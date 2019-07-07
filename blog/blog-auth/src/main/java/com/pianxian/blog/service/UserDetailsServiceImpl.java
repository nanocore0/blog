package com.pianxian.blog.service;

import com.pianxian.blog.entity.Permission;
import com.pianxian.blog.entity.User;
import com.pianxian.blog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findUserByUsername(username);
        List<Permission> permissions = userMapper.findPermissionsListByUsername(username);
        if(user != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (Permission permission : permissions) {
                String permissionTag = permission.getPermissionTag();
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permissionTag);
                authorities.add(authority);
            }
            user.setAuthorities(authorities);
        }
        return user;
    }
}
