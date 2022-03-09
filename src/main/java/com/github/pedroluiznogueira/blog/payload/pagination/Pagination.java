package com.github.pedroluiznogueira.blog.payload.pagination;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class Pagination<T> {
    private List<T> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private Boolean isLast;

    private Pagination(PaginationBuilder<T> paginationBuilder) {
        this.content = paginationBuilder.content;
        this.pageNumber = paginationBuilder.pageNumber;
        this.pageSize = paginationBuilder.pageSize;
        this.totalElements = paginationBuilder.totalElements;
        this.totalPages = paginationBuilder.totalPages;
        this.isLast = paginationBuilder.isLast;
    }

    public PaginationBuilder<T> builder() {
        PaginationBuilder<T> paginationBuilder = new PaginationBuilder<T>();
        return paginationBuilder;
    }

    @NoArgsConstructor
    public static class PaginationBuilder<T> {
        private List<T> content;
        private Integer pageNumber;
        private Integer pageSize;
        private Long totalElements;
        private Integer totalPages;
        private Boolean isLast;

        public PaginationBuilder<T> content(List<T> content) {
            this.content = content;
            return this;
        }

        public PaginationBuilder<T> pageNumber(Integer pageNumber) {
            this.pageNumber = pageNumber;
            return this;
        }

        public PaginationBuilder<T> pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public PaginationBuilder<T> totalElements(Long totalElements) {
            this.totalElements = totalElements;
            return this;
        }

        public PaginationBuilder<T> totalPages(Integer totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public PaginationBuilder<T> isLast(Boolean isLast) {
            this.isLast = isLast;
            return this;
        }

        public Pagination<T> build() {
            Pagination<T> pagination = new Pagination<T>(this);
            return  pagination;
        }
    }
}
