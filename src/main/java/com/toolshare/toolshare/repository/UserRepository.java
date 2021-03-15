package com.toolshare.toolshare.repository;

import com.toolshare.toolshare.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByName(String name);
    Iterable<User> findUsersByAge(int age);
}