package com.github.pedroluiznogueira.blog.service;

import com.github.pedroluiznogueira.blog.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPosts();
}
