package ru.yo_key.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yo_key.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
