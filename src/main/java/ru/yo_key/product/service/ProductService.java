package ru.yo_key.product.service;


import org.springframework.stereotype.Service;
import ru.yo_key.product.entity.Product;
import ru.yo_key.product.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Integer save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getBySegmentName(String segmentName) {
        return productRepository.getBySegmentName(segmentName);
    }

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public void delete(Integer id) {
        productRepository.delete(id);
    }
}
