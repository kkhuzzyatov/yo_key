package ru.yo_key.product.controller;

import org.springframework.web.bind.annotation.*;
import ru.yo_key.product.entity.Product;
import ru.yo_key.product.service.ProductService;
import ru.yo_key.segment.entity.Segment;
import ru.yo_key.segment.service.SegmentService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/save")
    public Integer save(@RequestBody Product request) {
        return productService.save(request);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/get_by_segment_name")
    public List<Product> getBySegmentName(@RequestParam String segmentName) { return productService.getBySegmentName(segmentName); }

    @CrossOrigin(origins = "*")
    @GetMapping("/get_all")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete")
    public void delete(@RequestParam Integer id) {
        productService.delete(id);
    }
}
