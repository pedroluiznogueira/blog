package com.github.pedroluiznogueira.blog.service;

import com.github.pedroluiznogueira.blog.payload.dto.PostDto;
import com.github.pedroluiznogueira.blog.entity.Post;
import com.github.pedroluiznogueira.blog.exception.ResourceNotFoundException;
import com.github.pedroluiznogueira.blog.repository.PostRepository;
import com.github.pedroluiznogueira.blog.service.abstraction.BusinessRule;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService implements BusinessRule<PostDto> {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PostService(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto create(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
        Post createdPost = postRepository.save(post);

        return modelMapper.map(createdPost, PostDto.class);
    }

    @Override
    public PostDto getById(Long postId) {
        Post foundPost = checkIfExistsById(postId);

        return modelMapper.map(foundPost, PostDto.class);
    }

    @Override
    public PostDto update(Long postId, PostDto postDto) {
        Post foundPost = checkIfExistsById(postId);

        foundPost.setTitle(postDto.getTitle());
        foundPost.setDescription(postDto.getDescription());
        foundPost.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(foundPost);

        return modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public String delete(Long postId) {
        Post foundPost = checkIfExistsById(postId);

        postRepository.delete(foundPost);

        return "post succesfully deleted";
    }

    public Post checkIfExistsById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
    }
}
