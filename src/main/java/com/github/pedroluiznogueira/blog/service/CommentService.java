package com.github.pedroluiznogueira.blog.service;

import com.github.pedroluiznogueira.blog.entity.Comment;
import com.github.pedroluiznogueira.blog.entity.Post;
import com.github.pedroluiznogueira.blog.exception.ResourceNotFoundException;
import com.github.pedroluiznogueira.blog.payload.dto.CommentDto;
import com.github.pedroluiznogueira.blog.repository.CommentRepository;
import com.github.pedroluiznogueira.blog.service.abstraction.BusinessRule;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import static java.util.stream.Collectors.toList;


@Service
public class CommentService implements BusinessRule<CommentDto> {

    private final CommentRepository commentRepository;
    private final PostService postService;
    private final ModelMapper modelMapper;

    public CommentService(CommentRepository commentRepository, PostService postService, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postService = postService;
        this.modelMapper = modelMapper;
    }

    public List<CommentDto> getAllByPostId(Long postId) {
        Post post = postService.checkIfExistsById(postId);
        List<CommentDto> commentsDtos = commentRepository.findAllByPostId(post.getId()).stream().map((comment) -> modelMapper.map(comment, CommentDto.class)).collect(toList());

        return commentsDtos;
    }

    @Override
    public CommentDto create(CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto, Comment.class);
        Comment createdComment = commentRepository.save(comment);

        return modelMapper.map(createdComment, CommentDto.class);
    }

    @Override
    public CommentDto getById(Long commentId) {
        Comment foundComment = checkIfExistsById(commentId);

        return modelMapper.map(foundComment, CommentDto.class);
    }

    @Override
    public CommentDto update(Long commentId, CommentDto commentDto) {
        Comment foundComment = checkIfExistsById(commentId);

        foundComment.setName(commentDto.getName());
        foundComment.setEmail(commentDto.getEmail());
        foundComment.setBody(commentDto.getBody());

        Comment updatedComment = commentRepository.save(foundComment);

        return modelMapper.map(updatedComment, CommentDto.class);
    }

    @Override
    public String delete(Long commentId) {
        Comment foundComment = checkIfExistsById(commentId);

        commentRepository.delete(foundComment);

        return "comment succesfully deleted";
    }

    private Comment checkIfExistsById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "id", commentId));
    };
}
