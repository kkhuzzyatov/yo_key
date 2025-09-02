package ru.yo_key.segment.model;

import jakarta.persistence.*;
import lombok.*;
import ru.yo_key.product.model.Product;

import java.util.List;

@Data
@Entity
@Table(name = "segments")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Segment {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false, unique = true)
    private String value;
    @OneToMany(mappedBy = "segment")
    private List<Product> products;
}
