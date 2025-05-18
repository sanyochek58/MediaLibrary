package com.example.MediaLibrary.repository;

import com.example.MediaLibrary.Entity.Model.MediaLib;
import com.example.MediaLibrary.Entity.Model.MediaType;
import com.example.MediaLibrary.Entity.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaLibRepository extends JpaRepository<MediaLib, Long> {
    List<MediaLib> findByType(MediaType type);
}
