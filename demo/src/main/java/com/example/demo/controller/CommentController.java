package com.example.demo.controller;


import com.example.demo.dto.CommentDTO;
import com.example.demo.model.Comment;
import com.example.demo.model.Posts;
import com.example.demo.model.User;
import com.example.demo.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/comment")
public class CommentController {

    private final CommentService service;

    @Autowired
    private CommentController(CommentService service){
        this.service = service;

    }


    @PostMapping("/{postId}")
    public ResponseEntity<String> comment(@RequestBody @Valid CommentDTO dto,
                                          @AuthenticationPrincipal User user,
                                          @PathVariable Long postId){
        try{
            service.createComment(dto, user, postId);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body("Erro ao comentar");
        }
        return ResponseEntity.ok("Comentário criado com sucesso");
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<Comment>> getAllComments(@AuthenticationPrincipal User user,
                                                        @PathVariable Long postId){
        return ResponseEntity.ok(service.findByPost(postId));
    }


}
