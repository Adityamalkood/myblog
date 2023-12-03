package com.Pratice_Api.Blog.Service;

import com.Pratice_Api.Blog.Payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostDto getPostById(long id);

    List<PostDto> GetAllPost (int pageNo, int PageSize, String sortBy, String sortDir);

    PostDto updatePost(PostDto postDto, long id);

    void DeletePostById(long id);
}
