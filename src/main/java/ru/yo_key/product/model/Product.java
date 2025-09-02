package ru.yo_key.product.model;

import jakarta.persistence.*;
import lombok.*;
import ru.yo_key.segment.model.Segment;
import ru.yo_key.size.model.Size;

import java.util.List;

@Data
@Entity
@Table(name = "products")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false, unique = true)
    private String nameText;
    @Column(nullable = false)
    private Double standardPriceValue;
    private Double discountPriceValue;
    @ManyToOne
    @JoinColumn(name = "segmentId")
    private Segment segment;
    @ManyToMany
    @JoinTable(
            name = "product_size",
            joinColumns = @JoinColumn(name = "productId"),
            inverseJoinColumns = @JoinColumn(name = "sizeId")
    )
    private List<Size> sizes;
    @Column(nullable = false, unique = true)
    private String mainImageLink;
    @Column(nullable = false, unique = true)
    private List<String> otherImageLinks;
}
