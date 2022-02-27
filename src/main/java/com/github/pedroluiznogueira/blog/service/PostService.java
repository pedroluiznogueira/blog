package com.github.pedroluiznogueira.blog.service;

import com.github.pedroluiznogueira.blog.dto.PostDto;
import com.github.pedroluiznogueira.blog.dto.mapper.PostMapper;
import com.github.pedroluiznogueira.blog.entity.Post;
import com.github.pedroluiznogueira.blog.exception.ResourceNotFoundException;
import com.github.pedroluiznogueira.blog.repository.PostRepository;
import com.github.pedroluiznogueira.blog.service.abstraction.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static java.util.stream.Collectors.toList;

@org.springframework.stereotype.Service
public class PostService implements Service<PostDto> {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Autowired
    public PostService(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    public PostDto create(PostDto postDto) {
        Post post = postMapper.toPost(postDto);
        Post createdPost = postRepository.save(post);
        PostDto createdPostDto = postMapper.toDto(createdPost);
        return createdPostDto;
    }

    @Override
    public List<PostDto> getAll() {
        List<Post> posts = postRepository.findAll();
        List<PostDto> postsDtos = posts.stream().map(postMapper::toDto).collect(toList());
        return postsDtos;
    }

    @Override
    public PostDto getById(Long postId) {
        Post foundPost = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        PostDto foundPostDto = postMapper.toDto(foundPost);
        return foundPostDto;
    }
}
