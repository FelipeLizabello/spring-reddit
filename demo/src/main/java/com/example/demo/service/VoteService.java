package com.example.demo.service;

import com.example.demo.dto.VoteDTO;
import com.example.demo.model.Posts;
import com.example.demo.model.User;
import com.example.demo.model.Vote;
import com.example.demo.repository.PostsRepository;
import com.example.demo.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoteService {

    private final VoteRepository repo;
    private final PostsRepository repoPosts;

    @Autowired
    public VoteService(VoteRepository repo, PostsRepository repoPosts){
        this.repo = repo;
        this.repoPosts = repoPosts;
    }

    public void vote(VoteDTO dto, User user, Long postId){
        Posts post = repoPosts.findById(postId).orElseThrow();
        Optional<Vote> existingVote = repo.findByUserAndPost(user, post);

        if(existingVote.isPresent()){
            Vote v = existingVote.get();
            post.setScore(post.getScore() - v.getValue());
            v.setValue(dto.getValue());
            repo.save(v);
            post.setScore(post.getScore() + dto.getValue());
            repoPosts.save(post);
        } else {
            Vote vote = new Vote();
            vote.setUser(user);
            vote.setPost(post);
            vote.setValue(dto.getValue());
            repo.save(vote);

            post.setScore(post.getScore() + dto.getValue());
            repoPosts.save(post);
        }
    }

    public List<Vote> listingVotes(Long postId){
        Posts post = repoPosts.findById(postId).orElseThrow();
        return repo.findByPost(post);
    }

    public Optional<Vote> findByuserAndPost(User user, Long postId){
        Posts posts = repoPosts.findById(postId).orElseThrow();

        return repo.findByUserAndPost(user, posts);

    }

}
