package com.pianxian.blog.service;

import com.github.pagehelper.PageInfo;
import com.pianxian.blog.entity.Blog;

import java.util.List;

public interface BlogService {

    Blog saveOrUpdate(Blog blog);

    Blog find(Integer id);

    List<Blog> findAllBlogs();

    List<Blog> findBlogsListByUserId(Integer id);

    void delete(Integer id);

    List<Blog> findBlogsListWithPage(int num, int size);

    List<Blog> findBlogsListWithPageAboutHotest(int num, int size);

    List<Blog> findBlogsListWithPageAboutNewest(int num, int size);

    List<Blog> findBlogsListByUserIdAndTitleLikeWithPage(Integer userId, String title, int num, int size);

    List<Blog> findBlogsListByUserIdAndCatalogIdWithPageAndOrder(Integer userId, Integer catalogId, String order, int num, int size);

    int countByUserId(Integer id);

    List<Blog> listTop5NewestBlogs();

    List<Blog> listTop5HotestBlogs();
}
