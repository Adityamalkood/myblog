package com.Pratice_Api.Blog.Repository;

import com.Pratice_Api.Blog.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
