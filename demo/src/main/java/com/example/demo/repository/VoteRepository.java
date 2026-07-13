package com.example.demo.repository;

import com.example.demo.model.Posts;
import com.example.demo.model.User;
import com.example.demo.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findByUserAndPost(User user, Posts posts);

    List<Vote> findByPost(Posts post);

}
