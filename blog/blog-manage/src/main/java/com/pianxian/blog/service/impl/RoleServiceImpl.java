package com.pianxian.blog.service.impl;

import com.pianxian.blog.entity.Role;
import com.pianxian.blog.mapper.RoleMapper;
import com.pianxian.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findRolesByUsername(String username) {
        return roleMapper.findRolesListByUsername(username);
    }
}
