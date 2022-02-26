package com.github.pedroluiznogueira.blog.dto.mapper;

import com.github.pedroluiznogueira.blog.dto.PostDto;
import com.github.pedroluiznogueira.blog.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public PostDto toDto(Post post) {
        return new PostDto(post.getTitle(), post.getDescription(), post.getContent());
    }

    public Post toPost(PostDto postDto) {
        return new Post(postDto.getTitle(), postDto.getDescription(), postDto.getContent());
    }
}
