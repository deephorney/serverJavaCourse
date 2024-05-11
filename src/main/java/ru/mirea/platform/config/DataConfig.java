package ru.mirea.platform.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mirea.platform.entity.Music;
import ru.mirea.platform.entity.MusicDTO;
import ru.mirea.platform.repository.MusicRepository;

import java.util.ArrayList;

@Configuration
public class DataConfig {
    @Bean
    public CommandLineRunner loadData(MusicRepository musicRepository) {
        return (args) -> {
            if (musicRepository.findAll().isEmpty()) {
                var musics = new ArrayList<Music>();
                musics.add(Music.builder()
                        .artistName("depressant")
                        .songName("Земля").build());
                musics.add(Music.builder()
                        .artistName("Pyrokinesis")
                        .songName("Легенда о богине мечей").build());
                musics.add(Music.builder()
                        .artistName("Шаман")
                        .songName("Мой бой").build());
                musics.add(Music.builder()
                        .artistName("Dabro")
                        .songName("Пусть услышат нас").build());
                musics.add(Music.builder()
                        .artistName("Pyrokinesis")
                        .songName("Прекрасное далеко").build());
                musicRepository.saveAll(musics);
            }
        };
    }
}
