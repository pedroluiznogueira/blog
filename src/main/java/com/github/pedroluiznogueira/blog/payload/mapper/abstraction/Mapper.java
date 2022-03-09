package com.github.pedroluiznogueira.blog.payload.mapper.abstraction;

public interface Mapper<D, E> {
    D toDto(E entity);
    E toEntity(D dto);
}
