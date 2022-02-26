package com.github.pedroluiznogueira.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Post {
    private Long id;
    private String title;
    private String description;
    private String content;
}
