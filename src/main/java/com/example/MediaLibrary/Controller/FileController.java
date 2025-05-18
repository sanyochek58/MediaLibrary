package com.example.MediaLibrary.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/values")
public class FileController {
    @Value("${upload.dir}")
    private String uploadDir;

    @GetMapping("/{filePath:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filePath){
        Path file = Paths.get(uploadDir).resolve(filePath);
        try{
            Resource resource = new UrlResource(file.toUri());
            return ResponseEntity.ok().contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM)).body(resource);
        }catch (IOException e){
            return ResponseEntity.notFound().build();
        }
    }
}
