package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository repo;
    private final JwtService jwt;

    @Autowired
    public AuthService(UserRepository repo, JwtService jwt){
        this.repo = repo;
        this.jwt = jwt;
    }

    public void register(UserDto dto){
        User user = new User();
        user.setEmail(dto.getEmail());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode(dto.getPassword());
        user.setPassword(password);

        repo.save(user);
    }

    public String login(LoginRequest login){
        User user = repo.findByEmail(login.getEmail()).orElseThrow();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if(encoder.matches(login.getPassword(), user.getPassword())){
            return jwt.generateToken(user);
        }

        throw new RuntimeException("Invalid credentials");
    }



}
