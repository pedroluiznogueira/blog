package com.github.pedroluiznogueira.blog.payload.mapper;

import com.github.pedroluiznogueira.blog.entity.Comment;
import com.github.pedroluiznogueira.blog.entity.Post;
import com.github.pedroluiznogueira.blog.payload.CommentDto;
import com.github.pedroluiznogueira.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    private PostService postService;

    @Autowired
    public CommentMapper(PostService postService) {
        this.postService = postService;
    }

    public CommentDto toDto(Comment comment) {
        return new CommentDto(comment.getName(), comment.getEmail(), comment.getBody(), comment.getPost().getId());
    }

    public Comment toComment(CommentDto commentDto) {
        Post commentPost = postService.checkIfExistsById(commentDto.getPostId());
        return new Comment(commentDto.getName(), commentDto.getEmail(), commentDto.getBody(), commentPost);
    }
}
