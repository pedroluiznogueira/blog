package com.github.pedroluiznogueira.blog.service.abstraction;

import com.github.pedroluiznogueira.blog.payload.Pagination;

public interface BusinessRule<T> {
    T create(T dto);

    T getById(Long id);

    T update(Long id, T dto);

    String delete(Long id);
}
