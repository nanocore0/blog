package com.pianxian.blog.service;

import com.pianxian.blog.entity.User;

import java.util.List;

public interface UserService {

    User findUserById(Integer id);

    User findUserByUsername(String username);

    User saveOrUpdateUser(User user);

    void deleteUser(Integer id);

    List<User> listUsers();

    List<User> findUserByNameLike(String name);
}
