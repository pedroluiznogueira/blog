package com.github.pedroluiznogueira.blog.controller;

import com.github.pedroluiznogueira.blog.dto.PostDto;
import com.github.pedroluiznogueira.blog.entity.Post;
import com.github.pedroluiznogueira.blog.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostServiceImpl postService;

    @Autowired
    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        PostDto postDtoResponse = postService.createPost(postDto);
        return ResponseEntity.status(201).body(postDtoResponse);
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> postsDtosResponse = postService.getAllPosts();
        return ResponseEntity.status(200).body(postsDtosResponse);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable ("postId") Long postId) {
        PostDto postDtoResponse = postService.getPostById(postId);
        return ResponseEntity.status(200).body(postDtoResponse);
    }
}
