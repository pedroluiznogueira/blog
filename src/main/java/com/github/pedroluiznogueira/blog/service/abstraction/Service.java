package com.github.pedroluiznogueira.blog.service.abstraction;

import com.github.pedroluiznogueira.blog.dto.PostDto;

import java.util.List;

public interface Service<T> {
    T create(T dto);

    List<T> getAll();

    T getById(Long id);

    T update(Long id, T dto);

    String delete(Long id);
}
