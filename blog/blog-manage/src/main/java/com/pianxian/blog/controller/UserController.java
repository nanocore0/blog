package com.pianxian.blog.controller;

import com.pianxian.blog.entity.Role;
import com.pianxian.blog.entity.User;
import com.pianxian.blog.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private VoteService voteService;

    @GetMapping("/user/{id}")
    public User query(@PathVariable Integer id) {
        User user = userService.findUserById(id);
        if(user != null) {
            List<Role> roles = roleService.findRolesByUsername(user.getUsername());
            user.setRoles(roles);
        }
        return user;
    }

    @PostMapping("/user")
    public User save(@RequestBody @Valid User user) {
        user = userService.saveOrUpdateUser(user);
        return user;
    }

    @PutMapping("user/reset/{id}")
    public User resetPassword(@PathVariable Integer id, @RequestParam String oldPassword, @RequestParam String newPassword, @RequestParam String repeatPassword) {
        if (oldPassword.trim().isEmpty() || newPassword.isEmpty() || repeatPassword.isEmpty() || !newPassword.equals(repeatPassword)) {
            throw new IllegalArgumentException("输入的密码为空或确认密码与新密码不一致");
        }
        User user = userService.findUserById(id);
        // 校验旧密码
        if (!new BCryptPasswordEncoder().matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("旧密码不匹配");
        }
        user.setPassword(newPassword);
        user = userService.saveOrUpdateUser(user);
        return user;
    }

    @PutMapping("/user/{id}")
    public User update(@RequestBody @Valid User user) {
        logger.info("" + user);
        if (user.getId() == null) {
            throw new IllegalArgumentException("id不能为空");
        }
        user = userService.saveOrUpdateUser(user);
        return user;
    }

    @DeleteMapping("user/{id}")
    public void delete(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @GetMapping("/users")
    public List<User> listAll() {
        List<User> users = userService.listUsers();
        for (User user : users) {
            List<Role> roles = roleService.findRolesByUsername(user.getUsername());
            user.setRoles(roles);
        }
        return users;
    }

    @GetMapping("/user/search")
    public List<User> search(@RequestParam String name) {
        List<User> users = userService.findUserByNameLike(name);
        return users;
    }

    @GetMapping("/user/info/{id}")
    public Map<String, Object> statistic(@PathVariable Integer id) {
        int blogSize = blogService.countByUserId(id);
        int commentSize = commentService.countByUserId(id);
        int voteSize = voteService.countByUserId(id);
        Map<String, Object> map = new HashMap<>();
        map.put("blogSize", blogSize);
        map.put("commentSize", commentSize);
        map.put("receivedVoteSize", voteSize);
        map.put("registerRank", id);
        return map;
    }
}
