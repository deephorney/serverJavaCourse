package ru.mirea.platform.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @OneToMany()
    @JoinColumn(name = "music_id")
    private List<Music> likedMusic=new ArrayList<>();

    public void addLikedMusic(Music music) {
        this.likedMusic.add(music);
    }

    public void removeLikedMusic(Music music) {
        this.likedMusic.remove(music);
    }
}
