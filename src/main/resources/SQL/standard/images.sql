CREATE TABLE images (
    id SERIAL PRIMARY KEY,
    image_link TEXT NOT NULL UNIQUE,
    is_main BOOLEAN DEFAULT FALSE,
    product_id INT
    REFERENCES products(id) ON DELETE CASCADE
);