package com.example.MediaLibrary.Entity.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "media_items")
@Getter
@Setter
public class MediaLib {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MediaType type;
    private String name;
    private String date;
    private String description;
    private String filename;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;
}