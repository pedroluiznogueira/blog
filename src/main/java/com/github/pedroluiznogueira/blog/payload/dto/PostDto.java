package com.github.pedroluiznogueira.blog.payload.dto;

import com.github.pedroluiznogueira.blog.entity.Comment;
import com.github.pedroluiznogueira.blog.exception.ResourceNotFoundException;
import com.github.pedroluiznogueira.blog.exception.bean.Error;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto extends ResponseEntityExceptionHandler {

    @NotEmpty @Size(min = 2, message = "title should have at least 2 characters")
    private String title;

    @NotEmpty @Size(min = 10, message = "description should have at least 10 characters")
    private String description;

    @NotEmpty
    private String content;

    private Set<CommentDto> comments = new HashSet<>();

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Error error = new Error(new Date(), ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(404).body(error);
    }

}
