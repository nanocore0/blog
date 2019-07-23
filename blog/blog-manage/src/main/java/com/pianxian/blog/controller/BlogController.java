package com.pianxian.blog.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.pagehelper.PageInfo;
import com.pianxian.blog.entity.Blog;
import com.pianxian.blog.service.BlogService;
import com.pianxian.blog.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BlogController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BlogService bLogService;

    @Autowired
    TagService tagService;

    @GetMapping("/blogs")
    //@JsonView(Blog.BlogListView.class)
    public PageInfo<Blog> listAll(@RequestParam(value = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                  @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                  @RequestParam(value = "order", required = false, defaultValue = "default") String order) {
        List<Blog> blogs;
        if (order.equalsIgnoreCase("new")) {
            blogs = bLogService.findBlogsListWithPageAboutNewest(pageNum, pageSize);
        } else if (order.equalsIgnoreCase("hot")) {
            blogs = bLogService.findBlogsListWithPageAboutHotest(pageNum, pageSize);
        } else {
            blogs = bLogService.findBlogsListWithPage(pageNum, pageSize);
        }
        PageInfo<Blog> page = new PageInfo<>(blogs, 5);
        return page;
    }

    @GetMapping("/blogs/user/{userId}")
    //@JsonView(Blog.BlogListView.class)
    public PageInfo<Blog> listByUser(@PathVariable Integer userId,
                                     @RequestParam(value = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                                     @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                     @RequestParam(value = "catalogId", required = false) Integer catalogId,
                                     @RequestParam(value = "order", required = false, defaultValue = "default") String order,
                                     @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
        logger.info("keyword:" + keyword + ", catalogId:" + catalogId + ", order:" + order);
        List<Blog> blogs;
        if (!keyword.trim().isEmpty()) {
            blogs = bLogService.findBlogsListByUserIdAndTitleLikeWithPage(userId, keyword, pageNum, pageSize);
        } else {
            blogs = bLogService.findBlogsListByUserIdAndCatalogIdWithPageAndOrder(userId, catalogId, order, pageNum, pageSize);
        }
        PageInfo<Blog> page = new PageInfo<>(blogs, 5);
        return page;
    }

    @GetMapping("/blog/{id}")
    @JsonView(Blog.BlogDetailView.class)
    public Blog query(@PathVariable Integer id) {
        Blog blog = bLogService.find(id);
        return blog;
    }

    @PostMapping("/blog")
    public Blog save(@RequestBody @Valid Blog blog) {
        System.out.println(blog);
        blog = bLogService.saveOrUpdate(blog);
        return blog;
    }

    @PutMapping("/blog/{id}")
    public Blog update(@RequestBody @Valid Blog blog) {
        bLogService.saveOrUpdate(blog);
        return blog;
    }

    @DeleteMapping("/blog/{id}")
    public void delete(@PathVariable Integer id) {
        bLogService.delete(id);
    }

    @GetMapping("/search")
    public PageInfo<Blog> search(@RequestParam String keyword) {
        return new PageInfo<Blog>(new ArrayList<Blog>(1), 5);
    }

    @GetMapping("/blogs/newest")
    @JsonView(Blog.BlogListView.class)
    public List<Blog> listNewest() {
        return bLogService.listTop5NewestBlogs();
    }

    @GetMapping("/blogs/hotest")
    @JsonView(Blog.BlogListView.class)
    public List<Blog> listHotest() {
        return bLogService.listTop5HotestBlogs();
    }
}
