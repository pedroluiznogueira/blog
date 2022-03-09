package com.github.pedroluiznogueira.blog.service.abstraction;

import com.github.pedroluiznogueira.blog.payload.pagination.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface Paginate<D, P> {
    Pagination<D> getAll(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);

    Sort getSortDirection(String sortBy, String sortDirection);

    Pagination<D> paginateContent(Integer pageNumber, Integer pageSize, Sort howToSort);

    Pagination<D> formatPaginationContent(Page<P> postPages);
}
