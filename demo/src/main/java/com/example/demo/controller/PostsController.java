package com.example.demo.controller;

import com.example.demo.dto.PostsDTO;
import com.example.demo.model.Posts;
import com.example.demo.model.User;
import com.example.demo.service.PostsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostsController {

    private final PostsService service;

    private PostsController(PostsService service){
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<String> post(@RequestBody @Valid PostsDTO dto,
                                       @AuthenticationPrincipal User user){
        try{
            service.createPost(dto, user);
        }catch(Exception e){
            return new ResponseEntity<String>("Erro ao concluir ação", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String>("Post efetuado com sucesso", HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<List<Posts>> getUserPosts(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(service.findByUserId(user));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Posts>> getAllPosts(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(service.findAll());
    }


}
