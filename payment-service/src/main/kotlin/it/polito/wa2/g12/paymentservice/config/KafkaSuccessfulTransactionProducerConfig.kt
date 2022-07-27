package it.polito.wa2.g12.paymentservice.config

import it.polito.wa2.g12.paymentservice.kafka.SuccessfulTransactionSerializer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaSuccessfulTransactionProducerConfig(
    @Value("\${kafka.bootstrapAddress}") private val servers: String
) {

    @Bean
    fun successfulTransactionProducerFactory(): ProducerFactory<String, Any> {
        val configProps: MutableMap<String, Any> = HashMap()
        configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = servers
        configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = SuccessfulTransactionSerializer::class.java
        return DefaultKafkaProducerFactory(configProps)
    }

    @Bean
    fun successfulTransactionKafkaTemplate(): KafkaTemplate<String, Any> {
        return KafkaTemplate(successfulTransactionProducerFactory())
    }
}