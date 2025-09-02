package ru.yo_key.product.service;

import org.springframework.stereotype.Service;
import ru.yo_key.product.dto.ProductDto;

import java.util.List;

@Service
public interface ProductService {
    List<ProductDto> findAll();
    void save(ProductDto productDTO);
    void update(ProductDto productDTO);
    void deleteById(Integer id);
}
