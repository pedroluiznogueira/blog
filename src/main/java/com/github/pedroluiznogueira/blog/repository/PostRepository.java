package com.github.pedroluiznogueira.blog.repository;

import com.github.pedroluiznogueira.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
