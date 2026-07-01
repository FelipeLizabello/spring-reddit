package com.example.demo.service;

import com.example.demo.dto.CommentDTO;
import com.example.demo.model.Comment;
import com.example.demo.model.Posts;
import com.example.demo.model.User;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private CommentRepository repo;
    private PostsRepository postsrepo;

    @Autowired
    public CommentService(CommentRepository repo, PostsRepository postsrepo){
        this.repo = repo;
        this.postsrepo = postsrepo;
    }

    public void createComment(CommentDTO dto, User user, Long postId){
        Comment comment = new Comment();
        Posts post = postsrepo.findById(postId).orElseThrow();

        comment.setContent(dto.getContent());
        comment.setPosts(post);
        comment.setUser(user);

        repo.save(comment);

    }

    public List<Comment> findByPost(Long postId){
        return repo.findByPosts_Id(postId);
    }


}
