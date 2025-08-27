CREATE TABLE product_size (
    id SERIAL PRIMARY KEY,
    product_id INT NOT NULL
        REFERENCES products(id)
        ON DELETE CASCADE,
    size_id INT NOT NULL
        REFERENCES sizes(id)
        ON DELETE CASCADE,
    UNIQUE (product_id, size_id)
);