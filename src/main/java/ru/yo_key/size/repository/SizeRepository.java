package ru.yo_key.size.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yo_key.size.model.Size;

public interface SizeRepository extends JpaRepository<Size, Integer> {
}
