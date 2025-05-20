package com.example.homemedialibrary.DTO;

import com.google.gson.annotations.SerializedName;

public class MediaLibDTO {
    private Long id;
    @SerializedName("type")
    private String mediaType;
    private String name;
    private String date;
    private String description;
    private String filename;

    public void setId(Long id) {
        this.id = id;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getId() {
        return id;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getFilename() {
        return filename;
    }
}

