package ru.mirea.platform.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.platform.entity.Music;
import ru.mirea.platform.entity.MusicDTO;
import ru.mirea.platform.services.MusicService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/music")
public class MusicController {
    @Autowired
    private MusicService musicService;

    @PostMapping("/create")
    public ResponseEntity<Music> create(@RequestBody MusicDTO dto){
        return new ResponseEntity<>(musicService.create(dto), HttpStatus.OK);
    }

    @GetMapping("/read")
    public ResponseEntity<List<Music>> readAll(){
        return new ResponseEntity<>(musicService.readAll(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable Long id){
        musicService.delete(id);
        return HttpStatus.OK;
    }


    @GetMapping("/song/{name}")
    public ResponseEntity<ByteArrayResource> getSong(@PathVariable String name) throws IOException {
        Path path = Paths.get("src/main/resources/static/music/" + name + ".mp3");
        byte[] data = Files.readAllBytes(path);
        ByteArrayResource resource = new ByteArrayResource(data);

        return ResponseEntity.ok()
                .body(resource);
    }


    @GetMapping("/image/{name}")
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable String name) throws IOException {
        Path path = Paths.get("src/main/resources/static/image/" + name + ".jpg");
        byte[] data = Files.readAllBytes(path);
        ByteArrayResource resource = new ByteArrayResource(data);

        return ResponseEntity.ok()
                .body(resource);
    }


}
