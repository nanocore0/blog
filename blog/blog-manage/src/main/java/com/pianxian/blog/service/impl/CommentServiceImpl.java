package com.pianxian.blog.service.impl;

import com.pianxian.blog.entity.Blog;
import com.pianxian.blog.entity.Comment;
import com.pianxian.blog.entity.User;
import com.pianxian.blog.mapper.BlogMapper;
import com.pianxian.blog.mapper.CommentMapper;
import com.pianxian.blog.mapper.UserMapper;
import com.pianxian.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    BlogMapper blogMapper;

    @Override
    public List<Comment> findCommentsListByBLogId(Integer blogId) {
        return commentMapper.findCommentsListByBlogId(blogId);
    }

    @Override
    @Transactional
    public Comment saveOrUpdate(Comment comment) {
        if(comment.getId() == null) {
            // 补全评论数据
            //User user = userMapper.findUserById(comment.getUserId());
            //comment.setAuthor(user.getNickname());
            //comment.setAvatar(user.getAvatar());

            commentMapper.insertComment(comment);
            // 评论量 +1
            Blog blog = blogMapper.findBlogById(comment.getBlogId());
            blog.setCommentSize(blog.getCommentSize() + 1);
            blogMapper.updateBlog(blog);
        } else {
            commentMapper.updateComment(comment);
        }
        return comment;
    }

    @Override
    @Transactional
    public void deleteComment(Integer id) {
        Comment comment = commentMapper.findCommentById(id);
        // 评论量 -1
        Blog blog = blogMapper.findBlogById(comment.getBlogId());
        blog.setCommentSize(blog.getCommentSize() - 1);
        blogMapper.updateBlog(blog);
        commentMapper.deleteComment(id);
    }

    @Override
    public int countByUserId(Integer id) {
        return commentMapper.countCommentsByUserId(id);
    }

    @Override
    public List<Comment> findCommentsListByUserId(Integer userId) {
        List<Comment> comments = commentMapper.findCommentsListByUserId(userId);
        return comments;
    }

    @Override
    public Comment findById(Integer id) {
        return commentMapper.findCommentById(id);
    }
}
