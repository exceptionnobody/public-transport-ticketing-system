DROP TABLE IF EXISTS tickets CASCADE;
DROP TABLE IF EXISTS orders CASCADE;

CREATE TABLE IF NOT EXISTS tickets
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    duration INT NULL,
    zones VARCHAR(255) NOT NULL,
    price NUMERIC(6,2) NOT NULL,
    min_age INT NULL,
    max_age INT NULL
);

CREATE TABLE IF NOT EXISTS orders
(
    id SERIAL PRIMARY KEY,
    ticket_id INT REFERENCES tickets (id) NOT NULL,
    quantity INT NOT NULL,
    username VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL
);

INSERT INTO tickets (name, type, duration, zones, price, min_age, max_age)
VALUES ('Ordinal ticket', 'Daily', 1, 'ABC', 2.50, null, null);
INSERT INTO tickets (name, type, duration, zones, price, min_age, max_age)
VALUES ('Weekend pass', 'Weekend', null, 'ABC', 3.50, null, 27);

INSERT INTO orders (ticket_id, quantity, username, status)
VALUES (1, 2, 'admin', 'SUCCESS');
INSERT INTO orders (ticket_id, quantity, username, status)
VALUES (2, 3, 'admin', 'SUCCESS');
INSERT INTO orders (ticket_id, quantity, username, status)
VALUES (1, 2, 'admin', 'FAILURE');
INSERT INTO orders (ticket_id, quantity, username, status)
VALUES (2, 1, 'admin', 'FAILURE');
INSERT INTO orders (ticket_id, quantity, username, status)
VALUES (1, 1, 'customer', 'FAILURE');
