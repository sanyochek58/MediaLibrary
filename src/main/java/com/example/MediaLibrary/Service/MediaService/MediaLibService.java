package com.example.MediaLibrary.Service.MediaService;

import com.example.MediaLibrary.Entity.DTO.MediaLibDTO;
import com.example.MediaLibrary.Entity.Model.MediaType;

import java.util.List;

public interface MediaLibService {
    List<MediaLibDTO> getAllMedia();
    List<MediaLibDTO> getMediaByType(MediaType mediaType);
}
