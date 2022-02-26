package com.github.pedroluiznogueira.blog.service.impl;

import com.github.pedroluiznogueira.blog.dto.PostDto;
import com.github.pedroluiznogueira.blog.repository.PostRepository;
import com.github.pedroluiznogueira.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        return null;
    }
}
