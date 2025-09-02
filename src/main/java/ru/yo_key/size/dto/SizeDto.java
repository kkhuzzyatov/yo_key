package ru.yo_key.size.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SizeDto {
    private Integer id;
    private String value;
}