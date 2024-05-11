package ru.mirea.platform.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.platform.entity.Music;
import ru.mirea.platform.entity.MusicDTO;
import ru.mirea.platform.services.MusicService;
import ru.mirea.platform.services.MyUserDetails;
import ru.mirea.platform.services.MyUserDetailsService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/secured")
public class MainController {

    @Autowired
    private MyUserDetailsService userService;


    @GetMapping("/user/name")
    public String userAccess(Principal principal){
            if(principal == null){
                return null;
            }
        return principal.getName();
    }


    @PostMapping("/music/{musicId}/like")
    public ResponseEntity<?> addLikedMusic(Principal principal, @PathVariable Long musicId) {
        userService.addLikedMusic(principal.getName(), musicId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/music/{musicId}/like")
    public ResponseEntity<?> removeLikedMusic(Principal principal, @PathVariable Long musicId) {
        userService.removeLikedMusic(principal.getName(), musicId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("/liked-music")
    public ResponseEntity<List<Music>> getLikedMusic(Principal principal) {
        List<Music> likedMusic = userService.getLikedMusic(principal.getName());
        return ResponseEntity.status(HttpStatus.OK).body(likedMusic);
    }

}
