package com.pianxian.blog.controller;

import com.pianxian.blog.entity.Role;
import com.pianxian.blog.entity.User;
import com.pianxian.blog.service.RoleService;
import com.pianxian.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/me")
    public Object me(Authentication authentication) {
        if(authentication.getPrincipal() instanceof String) {
            String username = (String) authentication.getPrincipal();
            User user = userService.findUserByUsername(username);
            List<Role> roles = roleService.findRolesByUsername(username);
            user.setRoles(roles);
            return user;
        }
        return null;
    }

    @RequestMapping("/login")
    public String login() {
        return "Please guide user to login";
    }

    @PostMapping("/register")
    public User register(User user) {
        user = userService.saveOrUpdateUser(user);
        return user;
    }
}
