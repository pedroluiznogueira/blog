package com.github.pedroluiznogueira.blog.controller.abstraction;

import com.github.pedroluiznogueira.blog.payload.Pagination;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface Controller<T> {
    ResponseEntity<T> create(@RequestBody T dto);

    ResponseEntity<Pagination> getAll(@RequestParam Integer pageNumber, @RequestParam Integer pageSize, @RequestParam String sortBy, @RequestParam String sortDirection);

    ResponseEntity<T> getById(Long id);

    ResponseEntity<T> update(@PathVariable ("id") Long id, @RequestBody T dto);

    ResponseEntity<String> delete(@PathVariable ("id") Long id);
}
