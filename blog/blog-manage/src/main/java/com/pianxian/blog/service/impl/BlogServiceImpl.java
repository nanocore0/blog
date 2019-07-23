package com.pianxian.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.pianxian.blog.entity.Blog;
import com.pianxian.blog.entity.Tag;
import com.pianxian.blog.entity.User;
import com.pianxian.blog.mapper.*;
import com.pianxian.blog.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    TagMapper tagMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    BlogTagMapper blogTagMapper;

    @Autowired
    VoteMapper voteMapper;

    @Override
    @Transactional
    public Blog saveOrUpdate(Blog blog) {
        System.out.println(blog);
        if (blog.getId() == null) {
            // 补全博客数据
            User user = userMapper.findUserById(blog.getUserId());
            //blog.setAuthor(user.getNickname());
            blog.setCreateTime(LocalDateTime.now());
            blog.setLastEditTime(blog.getCreateTime());
            blog.setReadSize(0);
            blog.setCommentSize(0);
            blog.setVoteSize(0);

            blogMapper.insertBlog(blog);
        } else {
            Blog originBlog = blogMapper.findBlogById(blog.getId());
            originBlog.setTitle(blog.getTitle());
            originBlog.setSummary(blog.getSummary());
            originBlog.setLastEditTime(LocalDateTime.now());
            originBlog.setContent(blog.getContent());
            originBlog.setHtmlContent(blog.getHtmlContent());
            originBlog.setCatalogId(blog.getCatalogId());

            blogMapper.updateBlog(blog);
        }
        // 无论新增还是更新，都要对标签进行关系整理
        List<Tag> toAddTagsList = new ArrayList<>();
        List<Tag> toDeleteTagsList = new ArrayList<>();
        List<Tag> oldTagList = tagMapper.findTagsListByBlogId(blog.getId());
        for (Tag oldTag : oldTagList) {
            boolean oldTagExistInNewList = false; // 旧标签是否在新列表里
            for (Tag tag : blog.getTags()) {
                if (oldTag.getName().equals(tag.getName())) {
                    oldTagExistInNewList = true;
                    break;
                }
            }
            if (!oldTagExistInNewList) {
                toDeleteTagsList.add(oldTag);
                //oldTagExistInNewList = false;
            }
        }
        for (Tag tag : blog.getTags()) {
            boolean newTagExistInOldList = false; // 新标签是否在旧列表里
            for (Tag oldTag : oldTagList) {
                if (oldTag.getName().equals(tag.getName())) {
                    newTagExistInOldList = true;
                    break;
                }
            }
            if (!newTagExistInOldList) {
                toAddTagsList.add(tag);
                //newTagExistInOldList = false;
            }
        }
        logger.info("要增加的标签: " + toAddTagsList);
        logger.info("要删除的标签: " + toDeleteTagsList);
        // 和新增的标签建立关系，如果标签不存在则创建标签
        for (Tag tag : toAddTagsList) {
            Tag originTag = tagMapper.findTagByName(tag.getName());
            if (originTag == null) {
                originTag = new Tag();
                originTag.setName(tag.getName());
                tagMapper.insertTag(originTag);
            }
            // 和新的标签建立关系
            originTag.setBlogId(blog.getId());
            blogTagMapper.insertATagWithBlog(originTag);
        }
        // 删除原来的关系
        for (Tag tag : toDeleteTagsList) {
            tag.setBlogId(blog.getId());
            blogTagMapper.deleteATagWithBlog(tag);
        }
        // 主键返回
        return blog;
    }

    @Override
    public Blog find(Integer id) {
        return blogMapper.findBlogById(id);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        blogTagMapper.deleteTagByBlogId(id);
        voteMapper.deleteVoteByBlogId(id);
        commentMapper.deleteCommentByBlogId(id);
        blogMapper.deleteBlog(id);
    }

    @Override
    public List<Blog> findAllBlogs() {
        return blogMapper.findAllBlogsList();
    }

    @Override
    public List<Blog> findBlogsListByUserId(Integer id) {
        return blogMapper.findBlogsListByUserId(id);
    }


    @Override
    public List<Blog> findBlogsListWithPage(int num, int size) {
        PageHelper.startPage(num, size);
        List<Blog> blogs = blogMapper.findAllBlogsList();
        return blogs;
    }


    @Override
    public List<Blog> findBlogsListWithPageAboutHotest(int num, int size) {
        PageHelper.startPage(num, size);
        List<Blog> blogs = blogMapper.findAllBlogsListOrderByCommentSizeAndVoteSizeAndReadSizeDesc();
        return blogs;
    }



    @Override
    public List<Blog> findBlogsListWithPageAboutNewest(int num, int size) {
        PageHelper.startPage(num, size);
        List<Blog> blogs = blogMapper.findAllBlogsListOrderByCreateTimeDesc();
        return blogs;
    }



    @Override
    public List<Blog> findBlogsListByUserIdAndTitleLikeWithPage(Integer userId, String title, int num, int size) {
        Blog blog = new Blog();
        blog.setUserId(userId);
        blog.setTitle(title);
        PageHelper.startPage(num, size);
        List<Blog> blogs = blogMapper.findBlogsListByUserIdAndTitleLike(blog);
        return blogs;
    }



    @Override
    public List<Blog> findBlogsListByUserIdAndCatalogIdWithPageAndOrder(Integer userId, Integer catalogId, String order, int num, int size) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("catalogId", catalogId);
        map.put("order", order);

        PageHelper.startPage(num, size);
        List<Blog> blogs = blogMapper.findBlogsListByUserIdAndCatalogIdOrdeByCreateTimeDescOrCommentSizeAndVoteSizeAndReadSizeDesc(map);
        return blogs;
    }

    @Override
    public int countByUserId(Integer id) {
        return blogMapper.countBlogsByUserId(id);
    }

    @Override
    public List<Blog> listTop5NewestBlogs() {
        PageHelper.startPage(0, 5);
        List<Blog> blogs = blogMapper.findAllBlogsListOrderByCreateTimeDesc();
        return blogs;
    }

    @Override
    public List<Blog> listTop5HotestBlogs() {
        PageHelper.startPage(0, 5);
        return blogMapper.findAllBlogsListOrderByCommentSizeAndVoteSizeAndReadSizeDesc();
    }
}
