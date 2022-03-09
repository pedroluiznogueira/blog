package com.github.pedroluiznogueira.blog.service;

import com.github.pedroluiznogueira.blog.entity.Comment;
import com.github.pedroluiznogueira.blog.payload.dto.CommentDto;
import com.github.pedroluiznogueira.blog.payload.mapper.CommentMapper;
import com.github.pedroluiznogueira.blog.repository.CommentRepository;
import com.github.pedroluiznogueira.blog.service.abstraction.BusinessRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentService implements BusinessRule<CommentDto> {

    private CommentRepository commentRepository;
    private CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public CommentDto create(CommentDto commentDto) {
        Comment comment = commentMapper.toEntity(commentDto);
        Comment createdComment = commentRepository.save(comment);

        return commentMapper.toDto(createdComment);
    }

    @Override
    public CommentDto getById(Long id) {
        return null;
    }

    @Override
    public CommentDto update(Long id, CommentDto dto) {
        return null;
    }

    @Override
    public String delete(Long id) {
        return null;
    }
}
