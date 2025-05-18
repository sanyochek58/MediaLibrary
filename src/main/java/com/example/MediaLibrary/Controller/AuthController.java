package com.example.MediaLibrary.Controller;

import com.example.MediaLibrary.Entity.DTO.LoginDTO;
import com.example.MediaLibrary.Entity.DTO.RegisterDTO;
import com.example.MediaLibrary.Service.Auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService authService){
        this.service = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDTO dto){
        service.register(dto);
        return ResponseEntity.ok("Пользователь " + dto.getUsername() + " успешно зарегистрирован");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO dto){
        service.login(dto);
        return ResponseEntity.ok("Пользователь c почтой " + dto.getEmail() + " вошёл в систему");
    }
}
