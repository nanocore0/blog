package com.pianxian.blog.mapper;

import com.pianxian.blog.entity.Blog;
import com.pianxian.blog.entity.Comment;

import java.util.List;

public interface CommentMapper {

    Comment findCommentById(Integer id);

    List<Comment> findCommentsListByUserId(Integer userId);

    List<Comment> findCommentsListByBlogId(Integer blogId);

    int insertComment(Comment comment);

    int updateComment(Comment comment);

    int deleteComment(Integer id);

    int deleteCommentByBlogId(Integer id);

    int countCommentsByUserId(Integer id);
}
