package ru.yo_key.uiElement.service;

import org.springframework.stereotype.Service;
import ru.yo_key.segment.entity.Segment;
import ru.yo_key.uiElement.entity.UiElement;
import ru.yo_key.uiElement.repository.UiElementRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UiElementService {
    private UiElementRepository uiElementRepository;

    public UiElementService(UiElementRepository uiElementRepository) { this.uiElementRepository = uiElementRepository; }

    public Integer save(String name, String value) {
        return uiElementRepository.save(new UiElement(null, name, value));
    }

    public String getAllUiElements() {
        List<UiElement> uiElements = uiElementRepository.getAll();
        return "id name value\n" + uiElements.stream()
                .map(uiElement -> uiElement.getId() + " " +uiElement.getName() + " " + uiElement.getValue())
                .collect(Collectors.joining("\n"));
    }

    public String getValue(String name) {
        UiElement uiElement = uiElementRepository.get(name);
        return uiElement != null ? uiElement.getValue() : "ui element not found";
    }

    public void delete(String name) {
        uiElementRepository.delete(name);
    }
}
