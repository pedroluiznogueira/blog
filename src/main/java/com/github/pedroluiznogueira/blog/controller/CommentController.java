package com.github.pedroluiznogueira.blog.controller;

import com.github.pedroluiznogueira.blog.controller.abstraction.Controller;
import com.github.pedroluiznogueira.blog.payload.dto.CommentDto;
import com.github.pedroluiznogueira.blog.payload.Pagination;
import com.github.pedroluiznogueira.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController implements Controller<CommentDto> {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    @PostMapping
    public ResponseEntity<CommentDto> create(@RequestBody CommentDto commentDto) {
        CommentDto commentDtoResponse = commentService.create(commentDto);
        return ResponseEntity.status(201).body(commentDtoResponse);
    }

    @Override
    public ResponseEntity<Pagination> getAll(
            Integer pageNumber,
            Integer pageSize,
            String sortBy,
            String sortDirection
    ) {
        return null;
    }

    @Override
    public ResponseEntity<CommentDto> getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<CommentDto> update(Long id, CommentDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        return null;
    }
}
