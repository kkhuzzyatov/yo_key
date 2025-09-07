package ru.yo_key.segment.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yo_key.segment.dto.SegmentDto;
import ru.yo_key.segment.service.SegmentService;

import java.util.List;

@RestController
@RequestMapping("/api/segments")
@AllArgsConstructor
public class SegmentController {
    private SegmentService service;

    @CrossOrigin(origins = "*")
    @GetMapping("/find_all")
    public List<SegmentDto> findAll() {
        return service.findAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/save")
    public void save(@RequestBody SegmentDto segmentDTO) {
        service.save(segmentDTO);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/update")
    public void update(@RequestBody SegmentDto segmentDTO) {
        service.update(segmentDTO);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete")
    public void delete(@RequestParam Integer id) {
        service.deleteById(id);
    }
}
