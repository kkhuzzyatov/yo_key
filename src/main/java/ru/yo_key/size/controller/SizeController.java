package ru.yo_key.size.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yo_key.size.dto.SizeDto;
import ru.yo_key.size.service.SizeService;

import java.util.List;

@RestController
@RequestMapping("/api/sizes")
@AllArgsConstructor
public class SizeController {
    private SizeService service;

    @CrossOrigin(origins = "*")
    @GetMapping("/find_all")
    public List<SizeDto> findAll() {
        return service.findAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/save")
    public void save(@RequestBody SizeDto sizeDto) {
        service.save(sizeDto);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/update")
    public void update(@RequestBody SizeDto sizeDto) {
        service.update(sizeDto);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete")
    public void delete(@RequestParam Integer id) {
        service.deleteById(id);
    }
}
