package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    private AuthController(AuthService service){
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<String> savePassword(@RequestBody @Valid UserDto dto){
        try{
            service.savePassword(dto);
        }catch(Exception e){
            return new ResponseEntity<String>("Erro ao salvar senha", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>("Senha salva com sucesso", HttpStatus.CREATED);
    }
}
