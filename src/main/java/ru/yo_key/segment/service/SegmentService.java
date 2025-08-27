package ru.yo_key.segment.service;

import org.springframework.stereotype.Service;
import ru.yo_key.segment.entity.Segment;
import ru.yo_key.segment.repository.SegmentRepository;

import java.util.List;

@Service
public class SegmentService {
    private final SegmentRepository segmentRepository;

    public SegmentService(SegmentRepository segmentRepository) {
        this.segmentRepository = segmentRepository;
    }

    public Integer save(Segment segment) {
        return segmentRepository.save(segment);
    }

    public List<Segment> getAll() {
        return segmentRepository.getAll();
    }

    public void delete(Integer id) {
        segmentRepository.delete(id);
    }
}
