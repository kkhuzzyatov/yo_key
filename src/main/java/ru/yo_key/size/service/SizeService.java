package ru.yo_key.size.service;

import org.springframework.stereotype.Service;
import ru.yo_key.size.dto.SizeDto;

import java.util.List;

@Service
public interface SizeService {
    List<SizeDto> findAll();
    void save(SizeDto sizeDto);
    void update(SizeDto sizeDto);
    void deleteById(Integer id);
}
