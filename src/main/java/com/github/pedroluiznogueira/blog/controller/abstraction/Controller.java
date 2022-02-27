package com.github.pedroluiznogueira.blog.controller.abstraction;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface Controller<T> {
    ResponseEntity<T> create(@RequestBody T dto);

    ResponseEntity<List<T>> getAll();

    ResponseEntity<T> getById(Long id);
}
