CREATE TABLE IF NOT EXISTS account
(
    id          SERIAL PRIMARY KEY,
    number      VARCHAR(100) NOT NULL,
    iban        VARCHAR(100) NOT NULL,
    balance     NUMERIC(15,2),
    currency    VARCHAR(100),
    type        VARCHAR(100),
    created_at  TIMESTAMP    NOT NULL,
    id_customer INTEGER      NOT NULL,
    CONSTRAINT fk_customer
        FOREIGN KEY (id_customer)
            REFERENCES customer (id)
);