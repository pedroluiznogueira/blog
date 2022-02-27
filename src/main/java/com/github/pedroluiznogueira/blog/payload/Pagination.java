package com.github.pedroluiznogueira.blog.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
public class Pagination {
    private final List<PostDto> content;
    private final Integer pageNumber;
    private final Integer pageSize;
    private final Long totalElements;
    private final Integer totalPages;
    private final Boolean isLast;

    private Pagination(PaginationBuilder paginationBuilder) {
        this.content = paginationBuilder.content;
        this.pageNumber = paginationBuilder.pageNumber;
        this.pageSize = paginationBuilder.pageSize;
        this.totalElements = paginationBuilder.totalElements;
        this.totalPages = paginationBuilder.totalPages;
        this.isLast = paginationBuilder.isLast;
    }

    public static PaginationBuilder builder() {
        return new PaginationBuilder();
    }

    @NoArgsConstructor
    public static class PaginationBuilder {
        private List<PostDto> content;
        private Integer pageNumber;
        private Integer pageSize;
        private Long totalElements;
        private Integer totalPages;
        private Boolean isLast;

        public PaginationBuilder content(List<PostDto> content) {
            this.content = content;
            return this;
        }

        public PaginationBuilder pageNumber(Integer pageNumber) {
            this.pageNumber = pageNumber;
            return this;
        }

        public PaginationBuilder pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public PaginationBuilder totalElements(Long totalElements) {
            this.totalElements = totalElements;
            return this;
        }

        public PaginationBuilder totalPages(Integer totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public PaginationBuilder isLast(Boolean isLast) {
            this.isLast = isLast;
            return this;
        }

        public Pagination build() {
            Pagination pagination = new Pagination(this);
            return  pagination;
        }
    }
}
