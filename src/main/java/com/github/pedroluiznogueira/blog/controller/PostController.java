package com.github.pedroluiznogueira.blog.controller;

import com.github.pedroluiznogueira.blog.controller.abstraction.Controller;
import com.github.pedroluiznogueira.blog.dto.PostDto;
import com.github.pedroluiznogueira.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController implements Controller<PostDto> {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Override
    @PostMapping
    public ResponseEntity<PostDto> create(@RequestBody PostDto postDto) {
        PostDto postDtoResponse = postService.create(postDto);
        return ResponseEntity.status(201).body(postDtoResponse);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<PostDto>> getAll(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize
    ) {
        List<PostDto> postsDtosResponse = postService.getAll(pageNumber, pageSize);
        return ResponseEntity.status(200).body(postsDtosResponse);
    }

    @Override
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getById(@PathVariable ("postId") Long postId) {
        PostDto postDtoResponse = postService.getById(postId);
        return ResponseEntity.status(200).body(postDtoResponse);
    }

    @Override
    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> update(@PathVariable ("postId") Long postId, @RequestBody PostDto postDto) {
        PostDto postDtoResponse = postService.update(postId, postDto);
        return ResponseEntity.status(200).body(postDtoResponse);
    }

    @Override
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> delete(@PathVariable ("postId") Long postId) {
        String response = postService.delete(postId);
        return ResponseEntity.status(200).body(response);
    }
}
