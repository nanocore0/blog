package com.pianxian.blog.service;

import com.pianxian.blog.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> findRolesByUsername(String username);
}
