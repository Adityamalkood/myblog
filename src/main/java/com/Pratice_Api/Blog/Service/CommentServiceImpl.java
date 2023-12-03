package com.Pratice_Api.Blog.Service;

import com.Pratice_Api.Blog.Entity.Comment;
import com.Pratice_Api.Blog.Entity.Post;
import com.Pratice_Api.Blog.Exception.ResourceNotFoundException;
import com.Pratice_Api.Blog.Payload.CommentDto;
import com.Pratice_Api.Blog.Payload.PostDto;
import com.Pratice_Api.Blog.Repository.CommentRepository;
import com.Pratice_Api.Blog.Repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepo;
    private PostRepository postRepo;
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepo, PostRepository postRepo, ModelMapper modelMapper) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto CreateComment(Long id, CommentDto commentDto) {

        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );


        Comment comment = new Comment();

       comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        comment.setPost(post);

        Comment savedComment = commentRepo.save(comment);
        CommentDto dto = new CommentDto();
        dto.setId(savedComment.getId());
        dto.setName(savedComment.getName());
        dto.setBody(savedComment.getBody());
        dto.setEmail(savedComment.getEmail());
        return dto;
    }

    @Override
    public List<CommentDto> findByPostId(long PostId) {
        Post post = postRepo.findById(PostId).orElseThrow(
                () -> new ResourceNotFoundException(PostId)
        );
        List<Comment> comment = commentRepo.findByPostId(PostId);
        List<CommentDto> dtos = comment.stream().map(comment1 -> mapToDto(comment1)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public CommentDto updateComment(long postId, long id, CommentDto commentDto) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(postId)
        );
        Comment comment = commentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        Comment updatedComment = commentRepo.save(comment);
        CommentDto dto = mapToDto(updatedComment);
        return dto;
    }

    //        Comment comment = mapToEntity(commentDto);
//        comment.getId();
//        comment.getName();
//        comment.getBody();
//        comment.getEmail();
//        comment.setPost(post);
//        Comment savedcomment = commentRepo.save(comment);
//        CommentDto dto = mapToDto(savedcomment);
//        return dto;
//    }
//
//
    Comment mapToEntity(CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto,Comment.class);
        return comment;
    }
    CommentDto mapToDto(Comment comment){
        CommentDto dto = modelMapper.map(comment,CommentDto.class);
        return dto;
    }


}
