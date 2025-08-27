package ru.yo_key.size.repository;

import ru.yo_key.size.entity.Size;

import java.util.List;

public interface SizeRepository {
    Integer save(Size size);
    List<Size> getAll();
    void delete(Integer id);
}
