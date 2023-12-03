package com.Pratice_Api.Blog.Controller;

import com.Pratice_Api.Blog.Payload.CommentDto;
import com.Pratice_Api.Blog.Payload.PostDto;
import com.Pratice_Api.Blog.Service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    private CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
@PostMapping("posts/{id}/comments")
   public ResponseEntity<CommentDto> CreateComment(@PathVariable Long id , @RequestBody CommentDto commentDto){
    CommentDto dto = commentService.CreateComment(id, commentDto);
    return new ResponseEntity<>(dto, HttpStatus.CREATED);

}
    @GetMapping ("posts/{PostId}/comments")
public List<CommentDto>findByPostId(@PathVariable  long PostId){
    List<CommentDto> dtos = commentService.findByPostId(PostId);
    return dtos;
}
@PutMapping("posts/{postId}/comments/{id}")
 public ResponseEntity<CommentDto>updateComment(@PathVariable (value = "postId") long postId,@PathVariable (value = "id") long id,@RequestBody CommentDto commentDto){
     CommentDto dto = commentService.updateComment(postId, id, commentDto);
     return  new ResponseEntity<>(dto,HttpStatus.OK);
 }

}
