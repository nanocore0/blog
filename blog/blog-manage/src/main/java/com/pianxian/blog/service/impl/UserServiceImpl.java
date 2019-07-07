package com.pianxian.blog.service.impl;

import com.pianxian.blog.entity.User;
import com.pianxian.blog.mapper.*;
import com.pianxian.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserMapper userMapper;

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    CatalogMapper catalogMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    @Transactional
    public User saveOrUpdateUser(User user) {
        if(user.getId() == null) {
            // 创建用户
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setCreateTime(LocalDateTime.now());
            user.setLastLoginTime(user.getCreateTime());
            user.setEnabled(true);
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);
            userMapper.insertUser(user);
        } else {
            User originUser = userMapper.findUserById(user.getId());
            // 传递上来的密码和数据库的密码不一样，认为修改了密码
            if (!user.getPassword().equals(originUser.getPassword())) {
                originUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            originUser.setUsername(user.getUsername());
            originUser.setEmail(user.getEmail());
            originUser.setNickname(user.getNickname());
            originUser.setAvatar(user.getAvatar());
            user = originUser;
            userMapper.updateUser(originUser);
        }
        return user;
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        // 测底删除用户，需要删除 博客，分类，评论，用户角色关系，点赞
        // TODO
        userRoleMapper.deleteUserRolesByUserId(id);
        userMapper.deleteUserById(id);
    }

    @Override
    public List<User> listUsers() {
        return userMapper.findUsersList();
    }

    @Override
    public List<User> findUserByNameLike(String name) {
        return userMapper.findUserByNameLike(name);
    }
}
