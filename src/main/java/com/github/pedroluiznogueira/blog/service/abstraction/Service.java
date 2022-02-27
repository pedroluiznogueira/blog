package com.github.pedroluiznogueira.blog.service.abstraction;

import com.github.pedroluiznogueira.blog.payload.PostResponse;

import java.util.List;

public interface Service<T> {
    T create(T dto);

    PostResponse getAll(Integer pageNumber, Integer pageSize);

    T getById(Long id);

    T update(Long id, T dto);

    String delete(Long id);
}
