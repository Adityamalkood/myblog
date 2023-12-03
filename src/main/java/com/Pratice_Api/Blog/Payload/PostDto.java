package com.Pratice_Api.Blog.Payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PostDto {
    public long id;
    @NotEmpty(message = "title should not be empty")
    public String title;
    @NotEmpty
    public String description;
    @NotEmpty
    public String content;
}
