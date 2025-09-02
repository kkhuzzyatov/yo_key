package ru.yo_key.segment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yo_key.segment.model.Segment;

public interface SegmentRepository extends JpaRepository<Segment, Integer> {
}
