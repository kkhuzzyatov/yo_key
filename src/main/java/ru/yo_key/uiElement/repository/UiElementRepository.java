package ru.yo_key.uiElement.repository;


import ru.yo_key.uiElement.entity.UiElement;

import java.util.List;

public interface UiElementRepository {
    Integer save(UiElement uiElement);
    List<UiElement> getAll();
    UiElement get(String name);
    void delete(String name);
}
