package ru.yo_key.size.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.yo_key.size.dto.SizeDto;
import ru.yo_key.size.model.Size;
import ru.yo_key.size.repository.SizeRepository;
import ru.yo_key.size.service.SizeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Primary
public class SizeServiceImpl implements SizeService {
    private SizeRepository repository;

    @Override
    public List<SizeDto> findAll() {
        return repository.findAll().stream()
                .map(size -> new SizeDto(size.getId(), size.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public void save(SizeDto sizeDto) {
        repository.save(Size.builder()
                .id(sizeDto.getId())
                .value(sizeDto.getValue())
                .build());
    }

    @Override
    public void update(SizeDto sizeDto) {
        repository.save(Size.builder()
                .id(sizeDto.getId())
                .value(sizeDto.getValue())
                .build());
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
