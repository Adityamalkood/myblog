package com.Pratice_Api.Blog.Controller;

import com.Pratice_Api.Blog.Payload.PostDto;
import com.Pratice_Api.Blog.Service.PostService;
import org.hibernate.engine.jdbc.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sun.awt.SunHints;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.List;

@RequestMapping("api/posts")
@RestController
public class PostController {
    private PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createPost(@Valid  @RequestBody PostDto postDto, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

        }
        PostDto dto = postService.createPost(postDto);
        return  new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public List<PostDto> GetAllPost(@RequestParam (value = "pageNo",defaultValue = "0",required=false) int PageNo,
                                    @RequestParam (value = "pageSize",defaultValue = "5",required = false)int PageSize,
                                    @RequestParam (value = "SortBy",defaultValue = "id",required = false)String SortBy,
                                    @RequestParam (value = "SortDir",defaultValue = "asc",required = false)String SortDir
    ){
        List<PostDto> postDtos = postService.GetAllPost(PageNo,PageSize,SortBy,SortDir);
           return postDtos;
    }
@GetMapping("/{id}")
    public  ResponseEntity<PostDto> GetPostById(@PathVariable long id){
        PostDto dto = postService.getPostById(id);
     return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public  ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto ,@PathVariable long id){
        PostDto dto = postService.updatePost(postDto, id);
        return  new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void DeletePostById(@PathVariable long id){
        postService.DeletePostById(id);
    }

}
