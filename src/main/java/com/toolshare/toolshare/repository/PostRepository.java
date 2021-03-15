package com.toolshare.toolshare.repository;

import com.toolshare.toolshare.entity.Post;
import com.toolshare.toolshare.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Integer> {
    Iterable<Post> findByUser(User user);
}
