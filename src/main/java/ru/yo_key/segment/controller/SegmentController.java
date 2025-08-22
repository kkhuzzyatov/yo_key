package ru.yo_key.segment.controller;

import org.springframework.web.bind.annotation.*;
import ru.yo_key.segment.entity.Segment;
import ru.yo_key.segment.service.SegmentService;

@RestController
@RequestMapping("/api/segments")
public class SegmentController {
    private final SegmentService segmentService;

    public SegmentController(SegmentService segmentService) {
        this.segmentService = segmentService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/save")
    public Integer saveSegment(@RequestBody Segment request) {
        return segmentService.save(request.getValue());
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/get")
    public String get(@RequestParam Integer id) {
        return segmentService.getValue(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/get_all")
    public String getAll() {
        return segmentService.getValuesOfAll();
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete")
    public void delete(@RequestParam Integer id) {
        segmentService.delete(id);
    }
}
