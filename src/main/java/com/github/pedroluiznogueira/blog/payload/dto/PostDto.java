package com.github.pedroluiznogueira.blog.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostDto {
    private String title;
    private String description;
    private String content;
}
