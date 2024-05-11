package ru.mirea.platform.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.platform.entity.Music;
import ru.mirea.platform.entity.MusicDTO;
import ru.mirea.platform.repository.MusicRepository;

import java.util.List;

@Service
public class MusicService {
    @Autowired
    private MusicRepository musicRepository;

    public Music create(MusicDTO dto){
        Music music = Music.builder()
                .artistName(dto.getArtistName())
                .songName(dto.getSongName())
                .build();
        return musicRepository.save(music);
    }

    public List<Music> readAll(){
        return musicRepository.findAll();
    }


    public void delete(Long id){
        musicRepository.deleteById(id);
    }
}
