package com.github.pedroluiznogueira.blog.controller;

import com.github.pedroluiznogueira.blog.controller.abstraction.Controller;
import com.github.pedroluiznogueira.blog.payload.dto.PostDto;
import com.github.pedroluiznogueira.blog.payload.pagination.Pagination;
import com.github.pedroluiznogueira.blog.service.PostPaginationService;
import com.github.pedroluiznogueira.blog.service.PostService;
import com.github.pedroluiznogueira.blog.utility.pagination.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController implements Controller<PostDto> {

    private final PostService postService;
    private final PostPaginationService postPaginationService;

    @Autowired
    public PostController(PostService postService, PostPaginationService postPaginationService) {
        this.postService = postService;
        this.postPaginationService = postPaginationService;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> create(@Valid @RequestBody PostDto postDto) {
        PostDto postDtoResponse = postService.create(postDto);
        return ResponseEntity.status(201).body(postDtoResponse);
    }

    @Override
    @GetMapping
    public ResponseEntity<Pagination> getAll(
            @RequestParam(value = "pageNumber", defaultValue = Const.DEFAULT_PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = Const.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = Const.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDirection", defaultValue = Const.DEFAULT_SORT_DIRECTION, required = false) String sortDirection
    ) {
        Pagination postsDtosResponse = postPaginationService.getAll(pageNumber, pageSize, sortBy, sortDirection);
        return ResponseEntity.status(200).body(postsDtosResponse);
    }

    @Override
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getById(@PathVariable ("postId") Long postId) {
        PostDto postDtoResponse = postService.getById(postId);
        return ResponseEntity.status(200).body(postDtoResponse);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> update(@PathVariable ("postId") Long postId, @Valid @RequestBody PostDto postDto) {
        PostDto postDtoResponse = postService.update(postId, postDto);
        return ResponseEntity.status(200).body(postDtoResponse);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> delete(@PathVariable ("postId") Long postId) {
        String response = postService.delete(postId);
        return ResponseEntity.status(200).body(response);
    }
}
