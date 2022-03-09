package com.github.pedroluiznogueira.blog.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto {
    private String name;
    private String email;
    private String body;
    private Long postId;
}
