package com.pianxian.blog.controller;

import com.pianxian.blog.common.Response;
import com.pianxian.blog.entity.Comment;
import com.pianxian.blog.entity.User;
import com.pianxian.blog.service.CommentService;
import com.pianxian.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @GetMapping("/comments/blog/{blogId}")
    public List<Comment> listABlogAllComments(@PathVariable("blogId") Integer blogId) {
        List<Comment> list = commentService.findCommentsListByBLogId(blogId);
        return list;
    }

    @GetMapping("/comments/user/{userId}")
    public List<Comment> listCommentsWithUser(@PathVariable("userId") Integer userId) {
        List<Comment> list = commentService.findCommentsListByUserId(userId);
        return list;
    }

    @GetMapping("/comment/{id}")
    public Comment query(@PathVariable("id") Integer id) {
        Comment comment = commentService.findById(id);
        return comment;
    }

    @PostMapping("/comment")
    public Comment saveComment(@RequestBody Comment comment) {
        comment = commentService.saveOrUpdate(comment);
        return comment;
    }

    @PutMapping("/comment/{id}")
    public Comment updateComment(@RequestBody Comment comment) {
        commentService.saveOrUpdate(comment);
        return comment;
    }

    @DeleteMapping("/comment/{id}")
    public void deleteComment(@PathVariable("id") Integer id) {
        commentService.deleteComment(id);
    }

}
