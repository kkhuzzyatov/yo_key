package ru.yo_key.segment.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SegmentDto {
    private Integer id;
    private String value;
}
