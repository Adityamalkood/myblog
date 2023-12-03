package com.Pratice_Api.Blog.Service;

import com.Pratice_Api.Blog.Payload.CommentDto;
import com.Pratice_Api.Blog.Payload.PostDto;

import java.util.List;

public interface CommentService {
    CommentDto CreateComment(Long id, CommentDto commentDto);
    List<CommentDto> findByPostId(long PostId);

    CommentDto updateComment(long postId, long id, CommentDto commentDto);
}
