package com.github.pedroluiznogueira.blog.service;

import com.github.pedroluiznogueira.blog.entity.Post;
import com.github.pedroluiznogueira.blog.payload.Pagination;
import com.github.pedroluiznogueira.blog.payload.PostDto;
import com.github.pedroluiznogueira.blog.payload.mapper.PostMapper;
import com.github.pedroluiznogueira.blog.repository.PostRepository;
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
public class PostPaginationService implements Paginate<PostDto, Post> {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Autowired
    public PostPaginationService(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    public Pagination<PostDto> getAll(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
        Sort howToSort = getSortDirection(sortBy, sortDirection);
        Pagination<PostDto> pagination = paginateContent(pageNumber, pageSize, howToSort);

        return pagination;
    }

    @Override
    public Sort getSortDirection(String sortBy, String sortDirection) {
        Sort howToSort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        return howToSort;
    }

    @Override
    public Pagination<PostDto> paginateContent(Integer pageNumber, Integer pageSize, Sort howToSort) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, howToSort);
        Page<Post> postPages = postRepository.findAll(pageable);
        Pagination<PostDto> pagination = formatPaginationContent(postPages);

        return pagination;
    }

    @Override
    public Pagination<PostDto> formatPaginationContent(Page<Post> postPages) {
        List<Post> contentToFormat = postPages.getContent();
        List<PostDto> formattedContent = contentToFormat.stream().map(postMapper::toDto).collect(toList());
        Pagination<PostDto> pagination = new Pagination<PostDto>().builder()
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
