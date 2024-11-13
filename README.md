# public-transport-ticketing-system

Repository for the project of the Web Applications II course at Polytechnic University of Turin (academic year
2021-2022).

## Group 12 members:

| Student ID | Surname | Name |
| --- | --- | --- |
| s286154 | Ballario | Marco |
| s277873 | Galazzo | Francesco |
| s276086 | Tangredi | Giovanni |
| s292522 | Ubertini | Pietro |

### Services

| Service           | Port |
|-------------------|------|
| discovery-service | 8761 |
| gateway-service   | 8080 |
| login-service     | 8081 |
| traveler-service  | 8082 |
| catalogue-service | 8083 |
| payment-service   | 8084 |
| bank-service      | 8085 |
| report-service    | 8086 |
| transit-service   | 8087 |
| machine-mock      | 8088 |

### Course Project 2021-22

Design and implement an web-based information system that supports a public transport
company in managing the ticketing process and in granting automatic access to the vehicles.
The system will support two kinds of human users, travelers and administrators, as well as a
set of embedded systems controlling the turnstiles at the entrance and exit gates.
Travelers will be able to register and create an account by providing a valid e-mail address
they are in control of. Once logged in, travelers can manage their profile, buy tickets and
travelcards, consult the list of their purchases and download single travel documents in the
form of QRCodes encoding a JWS (JSON Web Signature).
QRCode readers located at the entrance of the transport stations will validate the JWS and
drive/block the corresponding turnstiles. These devices will interact with the overall system
authenticating themselves as embedded systems, in order to get the secret used to check
the validity of the JWS and provide transit count information.
Administrators (i.e, employees of the transport company) will be enrolled via an
administrative end-point by other administrators (provided they have the enrolling capability).
At installation time, a single administrative username/password with enrolling capability will
be created in order to bootstrap the process.
Administrators can manage ticket and travelcard types, by creating, updating and modifying
their properties (validity period, price, usage conditions) as well as access reports about
purchases and transits, both of single travelers and of the company as a whole for a
selectable time period.
The system will be implemented using a microservice architecture and will guarantee the
needed level of scalability and availability.

