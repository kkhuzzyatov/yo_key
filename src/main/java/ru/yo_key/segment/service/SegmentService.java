package ru.yo_key.segment.service;

import org.springframework.stereotype.Service;
import ru.yo_key.segment.dto.SegmentDto;

import java.util.List;

@Service
public interface SegmentService {
    List<SegmentDto> findAll();
    void save(SegmentDto segmentDTO);
    void update(SegmentDto segmentDTO);
    void deleteById(Integer id);
}
