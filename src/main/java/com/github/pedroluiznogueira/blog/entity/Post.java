package com.github.pedroluiznogueira.blog.entity;

import lombok.Data;

@Data
public class Post {
    private Long id;
    private String title;
    private String description;
    private String content;
}
