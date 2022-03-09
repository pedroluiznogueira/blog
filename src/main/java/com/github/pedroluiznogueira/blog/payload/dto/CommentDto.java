package com.github.pedroluiznogueira.blog.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private String name;
    private String email;
    private String body;
    private Long postId;
}
