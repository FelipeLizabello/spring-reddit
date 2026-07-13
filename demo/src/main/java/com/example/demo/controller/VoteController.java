package com.example.demo.controller;


import com.example.demo.dto.VoteDTO;
import com.example.demo.model.User;
import com.example.demo.model.Vote;
import com.example.demo.service.VoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/vote")
public class VoteController {

    private final VoteService service;

    private VoteController(VoteService service){
        this.service = service;
    }

    @PostMapping("/{postId}")
    public ResponseEntity<String> vote(@RequestBody @Valid VoteDTO dto,
                                       @AuthenticationPrincipal User user,
                                       @PathVariable Long postId){
        try{
            service.vote(dto, user, postId);
        }catch(Exception e){
            return new ResponseEntity<String>("Erro ao concluir ação", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String>("Voto registrado com sucesso", HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<Vote>> voteListing(@PathVariable Long postId){
        return ResponseEntity.ok(service.listingVotes(postId));
    }

    @GetMapping("{postId}/me")
    public ResponseEntity<Vote> myVote(@AuthenticationPrincipal User user,
                                       @PathVariable Long postId){
        return ResponseEntity.ok(service.findByuserAndPost(user, postId).orElse(null));
    }


}
