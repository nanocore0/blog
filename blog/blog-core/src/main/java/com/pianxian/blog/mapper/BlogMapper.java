package com.pianxian.blog.mapper;

import com.pianxian.blog.entity.Blog;

import java.util.List;
import java.util.Map;

public interface BlogMapper {

    Blog findBlogById(Integer id);

    List<Blog> findAllBlogsList();

    List<Blog> findBlogsListByUserId(Integer userId);

    int insertBlog(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlog(Integer id);

    List<Blog> findAllBlogsListOrderByCreateTimeDesc();

    List<Blog> findAllBlogsListOrderByCommentSizeAndVoteSizeAndReadSizeDesc();

    List<Blog> findBlogsListByUserIdOrderByCreateTimeDesc(Integer userId);

    List<Blog> findBlogsListByUserIdOrderByCommentSizeAndVoteSizeAndReadSizeDesc(Integer userId);

    List<Blog> findBlogsListByUserIdAndTitleLike(Blog blog);

    List<Blog> findBlogsListByUserIdAndCatalogId(Blog blog);

    List<Blog> findBlogsListByUserIdAndCatalogIdOrderByCreateTimeDesc(Blog blog);

    List<Blog> findBlogsListByUserIdAndCatalogIdOrderByCommentSizeAndVoteSizeAndReadSizeDesc(Blog blog);

    List<Blog> findBlogsListByUserIdAndCatalogIdOrdeByCreateTimeDescOrCommentSizeAndVoteSizeAndReadSizeDesc(Map map);

    int countBlogsByUserId(Integer id);
}