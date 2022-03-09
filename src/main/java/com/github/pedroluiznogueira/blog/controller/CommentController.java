package com.github.pedroluiznogueira.blog.controller;

import com.github.pedroluiznogueira.blog.controller.abstraction.Controller;
import com.github.pedroluiznogueira.blog.payload.dto.CommentDto;
import com.github.pedroluiznogueira.blog.payload.pagination.Pagination;
import com.github.pedroluiznogueira.blog.service.CommentPaginationService;
import com.github.pedroluiznogueira.blog.service.CommentService;
import com.github.pedroluiznogueira.blog.utility.pagination.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController implements Controller<CommentDto> {

    private final CommentService commentService;
    private final CommentPaginationService commentPaginationService;

    @Autowired
    public CommentController(CommentService commentService, CommentPaginationService commentPaginationService) {
        this.commentService = commentService;
        this.commentPaginationService = commentPaginationService;
    }

    @Override
    @PostMapping
    public ResponseEntity<CommentDto> create(@Valid @RequestBody CommentDto commentDto) {
        CommentDto commentDtoResponse = commentService.create(commentDto);
        return ResponseEntity.status(201).body(commentDtoResponse);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<List<CommentDto>> getAllByPostId(@PathVariable ("postId") Long postId) {
        List<CommentDto> commentsDtosResponse = commentService.getAllByPostId(postId);
        return ResponseEntity.status(200).body(commentsDtosResponse);
    }

    @Override
    @GetMapping
    public ResponseEntity<Pagination> getAll(
            @RequestParam(value = "pageNumber", defaultValue = Const.DEFAULT_PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = Const.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = Const.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDirection", defaultValue = Const.DEFAULT_SORT_DIRECTION, required = false) String sortDirection
    ) {
        Pagination commentsDtosResponse = commentPaginationService.getAll(pageNumber, pageSize, sortBy, sortDirection);
        return ResponseEntity.status(200).body(commentsDtosResponse);
    }

    @Override
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto> getById(@PathVariable ("commentId") Long commentId) {
        CommentDto commentDtoResponse = commentService.getById(commentId);
        return ResponseEntity.status(200).body(commentDtoResponse);
    }

    @Override
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> update(@PathVariable ("commentId") Long commentId, @Valid @RequestBody CommentDto commentDto) {
        CommentDto commentDtoResponse = commentService.update(commentId, commentDto);
        return ResponseEntity.status(200).body(commentDtoResponse);
    }

    @Override
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> delete(@PathVariable ("commentId") Long commentId) {
        String response = commentService.delete(commentId);
        return ResponseEntity.status(200).body(response);
    }
}
