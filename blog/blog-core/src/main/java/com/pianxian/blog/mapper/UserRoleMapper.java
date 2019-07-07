package com.pianxian.blog.mapper;

import com.pianxian.blog.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper {

    void insertUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    int deleteUserRolesByUserId(Integer userId);

    int deleteUserRolesByRoleId(Integer roleId);
}
