package ru.mirea.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.platform.entity.Music;

import java.util.Optional;

public interface MusicRepository extends JpaRepository<Music, Long> {
    Optional<Music> findById(Long id);
}
