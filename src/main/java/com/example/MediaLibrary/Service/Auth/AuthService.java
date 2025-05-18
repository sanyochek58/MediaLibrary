package com.example.MediaLibrary.Service.Auth;

import com.example.MediaLibrary.Entity.DTO.LoginDTO;
import com.example.MediaLibrary.Entity.DTO.RegisterDTO;

public interface AuthService {
    void login(LoginDTO dto);
    void register(RegisterDTO dto);
}
