package com.github.pedroluiznogueira.blog.service;

import com.github.pedroluiznogueira.blog.dto.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);
}
