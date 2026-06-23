package com.example.demo.service;

import com.example.demo.dto.PostsDTO;
import com.example.demo.model.Posts;
import com.example.demo.model.User;
import com.example.demo.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsService {

    private final PostsRepository repo;

    @Autowired
    public PostsService(PostsRepository repo){
        this.repo = repo;
    }

    public void createPost(PostsDTO dto, User user){
        Posts posts = new Posts();

        posts.setContent(dto.getContent());
        posts.setTitle(dto.getTitle());
        posts.setUser(user);

        repo.save(posts);
    }

    public List<Posts> findAll(){
        return repo.findAll();
    }

    public List<Posts> findByUserId(User user){
        return repo.findByUser_Id(user.getId());
    }




}
