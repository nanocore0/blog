package com.pianxian.blog.service;

import com.pianxian.blog.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findCommentsListByBLogId(Integer blogId);

    List<Comment> findCommentsListByUserId(Integer userId);

    Comment findById(Integer id);

    Comment saveOrUpdate(Comment comment);

    void deleteComment(Integer id);

    int countByUserId(Integer id);
}
