package com.pianxian.blog.mapper;

import com.pianxian.blog.entity.Role;

import java.util.List;

public interface RoleMapper {

    List<Role> findRolesListByUsername(String username);

}
