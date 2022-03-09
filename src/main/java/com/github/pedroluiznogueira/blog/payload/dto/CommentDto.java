package com.github.pedroluiznogueira.blog.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    @NotEmpty(message = "name shouldn't be null or empty")
    private String name;

    @NotEmpty(message = "email shouldn't be null or empty")
    @Email(message = "email with invalid format")
    private String email;

    @NotEmpty(message = "body shouldn't be null or empty")
    @Size(min = 10, message = "body should have at least 10 characters")
    private String body;

    @NotNull(message = "postId shouldn't be null")
    private Long postId;
}
