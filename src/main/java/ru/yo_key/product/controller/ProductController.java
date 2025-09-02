package ru.yo_key.product.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yo_key.product.dto.ProductDto;
import ru.yo_key.product.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {
    private ProductService service;

    @CrossOrigin(origins = "*")
    @GetMapping("/find_all")
    public List<ProductDto> findAll() {
        return service.findAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/save")
    public void save(@RequestBody ProductDto product) {
        service.save(product);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/update")
    public void update(@RequestBody ProductDto productDTO) {
        service.update(productDTO);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/delete")
    public void delete(@RequestBody Integer id) {
        service.deleteById(id);
    }
}
