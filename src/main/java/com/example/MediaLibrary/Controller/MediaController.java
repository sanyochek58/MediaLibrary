package com.example.MediaLibrary.Controller;

import com.example.MediaLibrary.Entity.DTO.MediaLibDTO;
import com.example.MediaLibrary.Entity.Model.MediaType;
import com.example.MediaLibrary.Service.MediaService.MediaLibService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    private final MediaLibService mediaService;

    public MediaController(MediaLibService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<MediaLibDTO>> getAllMedia() {
        return ResponseEntity.ok(mediaService.getAllMedia());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<MediaLibDTO>> getMediaByType(@PathVariable MediaType type) {
        return ResponseEntity.ok(mediaService.getMediaByType(type));
    }
}
