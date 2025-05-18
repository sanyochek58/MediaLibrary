package com.example.MediaLibrary.Entity.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegisterDTO {
    private String username;
    private String email;
    private String password;
    private String date_of_birth;
}
