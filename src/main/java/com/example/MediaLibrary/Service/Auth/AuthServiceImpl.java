package com.example.MediaLibrary.Service.Auth;

import com.example.MediaLibrary.Entity.DTO.LoginDTO;
import com.example.MediaLibrary.Entity.DTO.RegisterDTO;
import com.example.MediaLibrary.Entity.Model.Users;
import com.example.MediaLibrary.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthServiceImpl(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Override
    @Transactional
    public void register(RegisterDTO dto){
        Optional<Users> email = usersRepository.findByEmail(dto.getEmail());
        if(email.isPresent()){
            throw new RuntimeException("Email уже используется");
        }

        Users user = new Users();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setDate_of_birth(dto.getDate_of_birth());

        usersRepository.save(user);
    }

    @Override
    @Transactional
    public void login(LoginDTO dto){
        Optional<Users> email = usersRepository.findByEmail(dto.getEmail());
        if(email.isEmpty()){
            throw new RuntimeException("Пользователя не существует");
        }

        Users user = email.get();
        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new RuntimeException("Неверный пароль");
        }
    }
}
