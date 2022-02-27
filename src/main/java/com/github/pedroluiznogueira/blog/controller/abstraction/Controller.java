package com.github.pedroluiznogueira.blog.controller.abstraction;

import com.github.pedroluiznogueira.blog.payload.PostResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface Controller<T> {
    ResponseEntity<T> create(@RequestBody T dto);

    ResponseEntity<PostResponse> getAll(@RequestParam Integer pageNumber, Integer pageSize);

    ResponseEntity<T> getById(Long id);

    ResponseEntity<T> update(@PathVariable ("id") Long id, @RequestBody T dto);

    ResponseEntity<String> delete(@PathVariable ("id") Long id);
}
