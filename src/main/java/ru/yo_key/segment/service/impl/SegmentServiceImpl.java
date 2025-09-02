package ru.yo_key.segment.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.yo_key.segment.dto.SegmentDto;
import ru.yo_key.segment.model.Segment;
import ru.yo_key.segment.repository.SegmentRepository;
import ru.yo_key.segment.service.SegmentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Primary
public class SegmentServiceImpl implements SegmentService {
    private SegmentRepository repository;

    @Override
    public List<SegmentDto> findAll() {
        return repository.findAll().stream()
                .map(segment -> new SegmentDto(segment.getId(), segment.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public void save(SegmentDto segmentDTO) {
        repository.save(Segment.builder()
                .id(segmentDTO.getId())
                .value(segmentDTO.getValue())
                .build());
    }

    @Override
    public void update(SegmentDto segmentDTO) {
        repository.save(Segment.builder()
                .id(segmentDTO.getId())
                .value(segmentDTO.getValue())
                .build());
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
