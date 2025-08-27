CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name_text VARCHAR(255) NOT NULL UNIQUE,
    standard_price_value DECIMAL(10, 2) NOT NULL,
    discount_percent INT DEFAULT 0,
    segment_id INT
    REFERENCES segments(id)
);