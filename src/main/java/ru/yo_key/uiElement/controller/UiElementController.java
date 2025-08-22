package ru.yo_key.uiElement.controller;

import org.springframework.web.bind.annotation.*;
import ru.yo_key.segment.entity.Segment;
import ru.yo_key.uiElement.entity.UiElement;
import ru.yo_key.uiElement.service.UiElementService;

@RestController
@RequestMapping("/api/ui_elements")
public class UiElementController {
    private UiElementService uiElementService;

    public UiElementController(UiElementService uiElementService) {
        this.uiElementService = uiElementService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/save")
    public Integer saveSegment(@RequestBody UiElement request) {
        return uiElementService.save(request.getName(), request.getValue());
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/get")
    public String get(@RequestParam String name) {
        return uiElementService.getValue(name);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/get_all")
    public String getAll() {
        return uiElementService.getAllUiElements();
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete")
    public void delete(@RequestParam String name) {
        uiElementService.delete(name);
    }
}
