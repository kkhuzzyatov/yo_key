package ru.yo_key.size.service;

import org.springframework.stereotype.Service;
import ru.yo_key.size.entity.Size;
import ru.yo_key.size.repository.SizeRepository;

import java.util.List;

@Service
public class SizeService {
    private final SizeRepository sizeRepository;

    public SizeService(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    public Integer save(Size size) {
        return sizeRepository.save(size);
    }

    public List<Size> getAll() {
        return sizeRepository.getAll();
    }

    public void delete(Integer id) {
        sizeRepository.delete(id);
    }
}
