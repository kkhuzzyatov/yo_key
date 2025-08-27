package ru.yo_key.size.controller;

import org.springframework.web.bind.annotation.*;
import ru.yo_key.size.entity.Size;
import ru.yo_key.size.service.SizeService;

import java.util.List;

@RestController
@RequestMapping("/api/sizes")
public class SizeController {
    private final SizeService sizeService;

    public SizeController(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/save")
    public Integer saveSize(@RequestBody Size size) {
        return sizeService.save(size);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/get_all")
    public List<Size> getAll() {
        return sizeService.getAll();
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete")
    public void delete(@RequestParam Integer id) {
        sizeService.delete(id);
    }
}
