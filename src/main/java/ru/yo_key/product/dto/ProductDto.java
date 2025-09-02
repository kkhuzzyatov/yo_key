package ru.yo_key.product.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Integer id;
    private String nameText;
    private Double standardPriceValue;
    private Double discountPriceValue;
    private Integer segmentId;
    private List<Integer> sizeIds;
    private String mainImageLink;
    private List<String> otherImageLinks;
}