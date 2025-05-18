package com.example.MediaLibrary.Service;

import com.example.MediaLibrary.Entity.Model.MediaLib;
import com.example.MediaLibrary.Entity.Model.MediaType;
import com.example.MediaLibrary.Entity.Model.Users;
import com.example.MediaLibrary.Service.MediaService.MediaLibService;
import com.example.MediaLibrary.repository.MediaLibRepository;
import com.example.MediaLibrary.repository.UsersRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class DataInitializer {
    private final MediaLibRepository mediaLibRepository;
    private final UsersRepository usersRepository;

    @Value("${upload.dir}")
    private String uploadDir;

    public DataInitializer(MediaLibRepository mediaLibRepository, UsersRepository usersRepository){
        this.mediaLibRepository = mediaLibRepository;
        this.usersRepository = usersRepository;
    }

    @PostConstruct
    public void init(){
        if(usersRepository.count() == 0){
            Users users = new Users();
            users.setUsername("admin");
            users.setEmail("admin@admin.ru");
            users.setPassword("admin");
            users.setDate_of_birth("03/11/2005");
            usersRepository.save(users);

            List<MediaLib> mediaList = List.of(
                    createMedia(MediaType.Music, "Музыка 1", "2020-01-01", "Классическая музыка", "song1.mp3", users),
                    createMedia(MediaType.Music, "Музыка 2", "2020-02-01", "Поп-музыка", "song2.mp3", users),
                    createMedia(MediaType.Book, "Книга 1", "2019-01-01", "Фантастика", "book1.pdf", users),
                    createMedia(MediaType.Book, "Книга 2", "2019-02-01", "Научная литература", "book2.pdf", users),
                    createMedia(MediaType.Movie, "Фильм 1", "2018-01-01", "Боевик", "movie1.MP4", users),
                    createMedia(MediaType.Movie, "Фильм 2", "2018-02-01", "Комедия", "movie2.MP4", users)
            );
            mediaLibRepository.saveAll(mediaList);
        }
    }

    private MediaLib createMedia(MediaType type, String name, String date, String description, String filename, Users users){
        MediaLib mediaLib = new MediaLib();
        mediaLib.setType(type);
        mediaLib.setName(name);
        mediaLib.setDate(date);
        mediaLib.setDescription(description);
        mediaLib.setFilename(filename);
        mediaLib.setUser(users);
        return mediaLib;
    }
}
