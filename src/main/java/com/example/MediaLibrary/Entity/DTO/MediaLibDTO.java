package com.example.MediaLibrary.Entity.DTO;

import com.example.MediaLibrary.Entity.Model.MediaType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MediaLibDTO {
    private Long id;
    private MediaType type;
    private String name;
    private String date;
    private String description;
    private String filename;
}
