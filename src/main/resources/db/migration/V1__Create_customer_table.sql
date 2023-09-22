CREATE TABLE IF NOT EXISTS customer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    address VARCHAR(200),
    dob DATE NOT NULL,
    id_document VARCHAR(100),
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    country VARCHAR(50) NOT NULL
);