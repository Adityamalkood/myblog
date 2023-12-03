package com.Pratice_Api.Blog.Service;

import com.Pratice_Api.Blog.Entity.Post;
import com.Pratice_Api.Blog.Exception.ResourceNotFoundException;
import com.Pratice_Api.Blog.Payload.PostDto;
import com.Pratice_Api.Blog.Repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class PostServiceImpl implements PostService{
    private PostRepository postRepo;
    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepo, ModelMapper modelMapper) {
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
    }




    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post savedPost = postRepo.save(post);
        PostDto dto = mapToDto(savedPost);
        return dto;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );
        PostDto dto = mapToDto(post);

        return dto;
    }

    @Override
    public List<PostDto> GetAllPost(int PageNo , int PageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy) : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(PageNo,PageSize, sort);
        Page<Post> posts = postRepo.findAll(pageable);
        List<Post> content = posts.getContent();
        List<PostDto> postDtos = content.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );
        Post updatedContent = mapToEntity(postDto);
        updatedContent.setId(post.getId());
        Post savedContent = postRepo.save(updatedContent);
        PostDto dto = mapToDto(savedContent);
        return dto;
    }

    @Override
    public void DeletePostById(long id) {
        Post post = postRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(id)
        );
           postRepo.deleteById(id);
    }

    Post mapToEntity(PostDto postDto ){
        Post post = modelMapper.map(postDto,Post.class);
//        post.setId(postDto.getId());
//         post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return  post;
    }

    PostDto mapToDto(Post post){
        PostDto dto = modelMapper.map(post,PostDto.class);
//        dto.setId(post.getId());
//         dto.setTitle(post.getTitle());
//        dto.setDescription(post.getDescription());
//        dto.setContent(post.getContent());
        return dto;

    }
}
