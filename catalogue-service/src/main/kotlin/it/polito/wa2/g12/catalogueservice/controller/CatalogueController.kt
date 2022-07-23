package it.polito.wa2.g12.catalogueservice.controller

import it.polito.wa2.g12.catalogueservice.dto.OrderDTO
import it.polito.wa2.g12.catalogueservice.dto.PaymentInfoDTO
import it.polito.wa2.g12.catalogueservice.dto.TicketDTO
import it.polito.wa2.g12.catalogueservice.service.impl.CatalogueServiceImpl
import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
class CatalogueController(val catalogueService: CatalogueServiceImpl) {

    @GetMapping("/tickets")
    fun getAllTickets(): Flow<TicketDTO> {
        return catalogueService.getAllTickets()
    }

    @GetMapping("/orders")
    @PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER')")
    fun getAllUserOrders(principal: Principal): Flow<OrderDTO> {
        return catalogueService.getAllUserOrders(principal.name)
    }

    @GetMapping("/orders/{orderId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER')")
    suspend fun getUserOrderById(@PathVariable orderId: Long, principal: Principal): OrderDTO? {
        return catalogueService.getUserOrder(principal.name, orderId)
    }

    @GetMapping("/admin/orders")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    fun getAllOrders(): Flow<OrderDTO> {
        return catalogueService.getAllOrders()
    }

    // We use the unique nickname of the user as userId
    @GetMapping("/admin/orders/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    fun getAllUserOrders(@PathVariable userId: String): Flow<OrderDTO> {
        return catalogueService.getAllUserOrders(userId)
    }

    // Use a JSON like this one to test this endpoint:
    // {"name":"Super ticket","type":"Daily","price":"20","zones":"XYZ","min_age":18,"max_age":30,"duration":24}
    @PostMapping("/admin/tickets")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    suspend fun addNewTicket(@RequestBody ticket: TicketDTO): ResponseEntity<TicketDTO?> {
        val newTicket = catalogueService.addNewTicket(ticket)
        // In case the information provided are incorrect no ticket will be added
        // The information provided are incorrect when in 2 cases:
        // 1) When a provided daily ticket does not have a duration set
        // 2) When a not-daily ticket has the duration set
        return if (newTicket == null) ResponseEntity(null, HttpStatus.UNPROCESSABLE_ENTITY)
        else return ResponseEntity(newTicket, HttpStatus.OK)
    }

    @PostMapping("/shop")
    @PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER')")
    suspend fun shopTickets(
        @RequestHeader("Authorization") authorizationHeader: String,
        @RequestBody paymentInfo: PaymentInfoDTO,
        principal: Principal
    ): OrderDTO {
        return catalogueService.shopTickets(
            principal.name,
            paymentInfo,
            authorizationHeader
        )
    }
}