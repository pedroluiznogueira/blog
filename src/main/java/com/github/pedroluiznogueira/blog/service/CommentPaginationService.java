package com.github.pedroluiznogueira.blog.service;

import com.github.pedroluiznogueira.blog.entity.Comment;
import com.github.pedroluiznogueira.blog.entity.Post;
import com.github.pedroluiznogueira.blog.payload.Pagination;
import com.github.pedroluiznogueira.blog.payload.dto.CommentDto;
import com.github.pedroluiznogueira.blog.payload.dto.PostDto;
import com.github.pedroluiznogueira.blog.payload.mapper.CommentMapper;
import com.github.pedroluiznogueira.blog.repository.CommentRepository;
import com.github.pedroluiznogueira.blog.service.abstraction.Paginate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CommentPaginationService implements Paginate<CommentDto, Comment> {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentPaginationService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public Pagination<CommentDto> getAll(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
        Sort howToSort = getSortDirection(sortBy, sortDirection);
        Pagination<CommentDto> pagination = paginateContent(pageNumber, pageSize, howToSort);

        return pagination;
    }

    @Override
    public Sort getSortDirection(String sortBy, String sortDirection) {
        Sort howToSort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        return howToSort;
    }

    @Override
    public Pagination<CommentDto> paginateContent(Integer pageNumber, Integer pageSize, Sort howToSort) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, howToSort);
        Page<Comment> postPages = commentRepository.findAll(pageable);
        Pagination<CommentDto> pagination = formatPaginationContent(postPages);

        return pagination;
    }

    @Override
    public Pagination<CommentDto> formatPaginationContent(Page<Comment> postPages) {
        List<Comment> contentToFormat = postPages.getContent();
        List<CommentDto> formattedContent = contentToFormat.stream().map(commentMapper::toDto).collect(toList());
            Pagination<CommentDto> pagination = new Pagination<CommentDto>().builder()
                .content(formattedContent)
                .pageNumber(postPages.getNumber())
                .pageSize(postPages.getSize())
                .totalElements(postPages.getTotalElements())
                .totalPages(postPages.getTotalPages())
                .isLast(postPages.isLast())
                .build();

        return pagination;
    }
}
