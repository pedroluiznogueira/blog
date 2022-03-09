package com.github.pedroluiznogueira.blog.service;

import com.github.pedroluiznogueira.blog.entity.Comment;
import com.github.pedroluiznogueira.blog.entity.Post;
import com.github.pedroluiznogueira.blog.exception.ResourceNotFoundException;
import com.github.pedroluiznogueira.blog.payload.dto.CommentDto;
import com.github.pedroluiznogueira.blog.payload.mapper.CommentMapper;
import com.github.pedroluiznogueira.blog.repository.CommentRepository;
import com.github.pedroluiznogueira.blog.service.abstraction.BusinessRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;


@Service
public class CommentService implements BusinessRule<CommentDto> {

    private CommentRepository commentRepository;
    private CommentMapper commentMapper;
    private PostService postService;

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper, PostService postService) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.postService = postService;
    }

    public List<CommentDto> getAllByPostId(Long postId) {
        Post post = postService.checkIfExistsById(postId);
        List<CommentDto> commentsDtos = commentRepository.findAllByPostId(post.getId()).stream().map(commentMapper::toDto).collect(toList());

        return commentsDtos;
    }

    @Override
    public CommentDto create(CommentDto commentDto) {
        Comment comment = commentMapper.toEntity(commentDto);
        Comment createdComment = commentRepository.save(comment);

        return commentMapper.toDto(createdComment);
    }

    @Override
    public CommentDto getById(Long commentId) {
        Comment foundComment = checkIfExistsById(commentId);

        return commentMapper.toDto(foundComment);
    }

    @Override
    public CommentDto update(Long id, CommentDto dto) {
        return null;
    }

    @Override
    public String delete(Long id) {
        return null;
    }

    private Comment checkIfExistsById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "id", commentId));
    };
}
