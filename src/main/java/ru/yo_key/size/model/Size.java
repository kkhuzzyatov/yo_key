package ru.yo_key.size.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "sizes")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Size {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false, unique = true)
    private String value;
}
