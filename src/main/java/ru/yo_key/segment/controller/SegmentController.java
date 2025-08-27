package ru.yo_key.segment.controller;

import org.springframework.web.bind.annotation.*;
import ru.yo_key.segment.entity.Segment;
import ru.yo_key.segment.service.SegmentService;

import java.util.List;

@RestController
@RequestMapping("/api/segments")
public class SegmentController {
    private final SegmentService segmentService;

    public SegmentController(SegmentService segmentService) {
        this.segmentService = segmentService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/save")
    public Integer saveSegment(@RequestBody Segment segment) {
        return segmentService.save(segment);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/get_all")
    public List<Segment> getAll() {
        return segmentService.getAll();
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete")
    public void delete(@RequestParam Integer id) {
        segmentService.delete(id);
    }
}
