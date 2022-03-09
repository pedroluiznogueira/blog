package com.github.pedroluiznogueira.blog.service;

import com.github.pedroluiznogueira.blog.payload.dto.PostDto;
import com.github.pedroluiznogueira.blog.payload.mapper.PostMapper;
import com.github.pedroluiznogueira.blog.entity.Post;
import com.github.pedroluiznogueira.blog.exception.ResourceNotFoundException;
import com.github.pedroluiznogueira.blog.repository.PostRepository;
import com.github.pedroluiznogueira.blog.service.abstraction.BusinessRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService implements BusinessRule<PostDto> {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Autowired
    public PostService(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    public PostDto create(PostDto postDto) {
        Post post = postMapper.toEntity(postDto);
        Post createdPost = postRepository.save(post);

        return postMapper.toDto(createdPost);
    }

    @Override
    public PostDto getById(Long postId) {
        Post foundPost = checkIfExistsById(postId);

        return postMapper.toDto(foundPost);
    }

    @Override
    public PostDto update(Long postId, PostDto postDto) {
        Post foundPost = checkIfExistsById(postId);

        foundPost.setTitle(postDto.getTitle());
        foundPost.setDescription(postDto.getDescription());
        foundPost.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(foundPost);

        return postMapper.toDto(updatedPost);
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
