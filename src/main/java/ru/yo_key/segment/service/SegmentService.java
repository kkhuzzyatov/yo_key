package ru.yo_key.segment.service;

import org.springframework.stereotype.Service;
import ru.yo_key.segment.entity.Segment;
import ru.yo_key.segment.repository.SegmentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SegmentService {
    private final SegmentRepository segmentRepository;

    public SegmentService(SegmentRepository segmentRepository) {
        this.segmentRepository = segmentRepository;
    }

    public Integer save(String value) {
        return segmentRepository.save(new Segment(null, value));
    }

    public String getValuesOfAll() {
        List<Segment> segments = segmentRepository.getAll();
        return segments.stream()
                .map(segment -> segment.getValue())
                .collect(Collectors.joining(" "));
    }

    public String getValue(Integer id) {
        Segment segment = segmentRepository.get(id);
        return segment != null ? segment.getValue() : "Segment not found";
    }

    public void delete(Integer id) {
        segmentRepository.delete(id);
    }
}
