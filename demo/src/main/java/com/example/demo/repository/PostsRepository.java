package com.example.demo.repository;

import com.example.demo.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    List<Posts> findByUser_Id(Long id);

}
