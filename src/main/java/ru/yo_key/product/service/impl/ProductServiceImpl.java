package ru.yo_key.product.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.yo_key.product.dto.ProductDto;
import ru.yo_key.product.model.Product;
import ru.yo_key.product.repository.ProductRepository;
import ru.yo_key.product.service.ProductService;
import ru.yo_key.segment.repository.SegmentRepository;
import ru.yo_key.size.repository.SizeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Primary
public class ProductServiceImpl implements ProductService {
    private ProductRepository repository;
    private SegmentRepository segmentRepository;
    private SizeRepository sizeRepository;

    @Override
    public List<ProductDto> findAll() {
        return repository.findAll().stream()
                .map(product -> new ProductDto(
                        product.getId(),
                        product.getNameText(),
                        product.getStandardPriceValue(),
                        product.getDiscountPriceValue(),
                        product.getSegment().getId(),
                        product.getSizes().stream().map(size -> size.getId()).toList(),
                        product.getMainImageLink(),
                        product.getOtherImageLinks()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void save(ProductDto productDto) {
        repository.save(new Product(
                productDto.getId(),
                productDto.getNameText(),
                productDto.getStandardPriceValue(),
                productDto.getDiscountPriceValue(),
                segmentRepository.findById(productDto.getSegmentId()).orElse(null),
                productDto.getSizeIds().stream()
                        .map(sizeId -> sizeRepository.findById(sizeId).orElse(null))
                        .collect(Collectors.toList()),
                productDto.getMainImageLink(),
                productDto.getOtherImageLinks()
        ));
    }

    @Override
    public void update(ProductDto productDto) {
        repository.save(new Product(
                productDto.getId(),
                productDto.getNameText(),
                productDto.getStandardPriceValue(),
                productDto.getDiscountPriceValue(),
                segmentRepository.findById(productDto.getSegmentId()).orElse(null),
                productDto.getSizeIds().stream()
                        .map(sizeId -> sizeRepository.findById(sizeId).orElse(null))
                        .collect(Collectors.toList()),
                productDto.getMainImageLink(),
                productDto.getOtherImageLinks()
        ));
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
