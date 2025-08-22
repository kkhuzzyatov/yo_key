package ru.yo_key.segment.repository;

import ru.yo_key.segment.entity.Segment;

import java.util.List;

public interface SegmentRepository {
    Integer save(Segment segment);
    List<Segment> getAll();
    Segment get(Integer id);
    void delete(Integer id);
}
