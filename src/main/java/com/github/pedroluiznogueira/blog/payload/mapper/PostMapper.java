package com.github.pedroluiznogueira.blog.payload.mapper;

import com.github.pedroluiznogueira.blog.payload.dto.PostDto;
import com.github.pedroluiznogueira.blog.entity.Post;
import com.github.pedroluiznogueira.blog.payload.mapper.abstraction.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PostMapper implements Mapper<PostDto, Post> {

    public PostDto toDto(Post post) {
        return new PostDto(post.getTitle(), post.getDescription(), post.getContent());
    }

    public Post toEntity(PostDto postDto) {
        return new Post(postDto.getTitle(), postDto.getDescription(), postDto.getContent());
    }
}
