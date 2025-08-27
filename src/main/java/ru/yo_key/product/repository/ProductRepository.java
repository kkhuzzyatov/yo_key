package ru.yo_key.product.repository;

import ru.yo_key.product.entity.Product;

import java.util.List;

public interface ProductRepository {
    Integer save(Product product);
    List<Product> getBySegmentName(String segmentName);
    List<Product> getAll();
    void delete(Integer id);
}
