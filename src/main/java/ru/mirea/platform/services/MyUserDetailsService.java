package ru.mirea.platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mirea.platform.entity.Music;
import ru.mirea.platform.entity.MyUser;
import ru.mirea.platform.repository.MusicRepository;
import ru.mirea.platform.repository.UserRepository;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private MusicRepository musicRepository;

    public void addLikedMusic(String userName, Long musicId) {
        MyUser user = repository.findByUsername(userName)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден: " + userName));
        Music music = musicRepository.findById(musicId)
                .orElseThrow(() -> new IllegalArgumentException("Музыкальный трек не найден: " + musicId));
        user.addLikedMusic(music);
        repository.save(user);
    }

    public void removeLikedMusic(String userName, Long musicId) {
        MyUser user = repository.findByUsername(userName)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден: " + userName));
        Music music = musicRepository.findById(musicId)
                .orElseThrow(() -> new IllegalArgumentException("Музыкальный трек не найден: " + musicId));
        user.removeLikedMusic(music);
        repository.save(user);
    }

    public List<Music> getLikedMusic(String userName) {
        MyUser user = repository.findByUsername(userName)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден: " + userName));
        return user.getLikedMusic();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
        return MyUserDetails.build(user);
    }
}
