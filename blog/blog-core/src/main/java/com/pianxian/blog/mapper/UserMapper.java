package com.pianxian.blog.mapper;

import com.pianxian.blog.entity.Permission;
import com.pianxian.blog.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    User findUserById(Integer id);

    int insertUser(User user);

    int deleteUserById(Integer id);

    int updateUser(User user);

    List<User> findUsersList();

    User findUserByUsername(String username);

    List<Permission> findPermissionsListByUsername(String username);

    List<User> findUserByNameLike(String name);
}
