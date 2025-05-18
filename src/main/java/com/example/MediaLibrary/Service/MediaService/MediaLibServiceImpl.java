package com.example.MediaLibrary.Service.MediaService;


import com.example.MediaLibrary.Entity.DTO.MediaLibDTO;
import com.example.MediaLibrary.Entity.Model.MediaLib;
import com.example.MediaLibrary.Entity.Model.MediaType;
import com.example.MediaLibrary.repository.MediaLibRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MediaLibServiceImpl implements MediaLibService{
    private final MediaLibRepository mediaLibRepository;

    public MediaLibServiceImpl(MediaLibRepository mediaLibRepository){
        this.mediaLibRepository = mediaLibRepository;
    }

    @Override
    public List<MediaLibDTO> getAllMedia(){
        return mediaLibRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<MediaLibDTO> getMediaByType(MediaType type){
        return mediaLibRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    private MediaLibDTO toDTO(MediaLib media){
        MediaLibDTO dto = new MediaLibDTO();
        dto.setId(media.getId());
        dto.setType(dto.getType());
        dto.setName(dto.getName());
        dto.setDate(dto.getDate());
        dto.setDescription(dto.getDescription());
        dto.setFilename(dto.getFilename());
        return dto;
    }
}
