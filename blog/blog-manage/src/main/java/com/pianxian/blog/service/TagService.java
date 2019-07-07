package com.pianxian.blog.service;

import com.pianxian.blog.entity.Tag;

import java.util.List;

public interface TagService {

    void save(Tag tag);

    void delete(Integer id);

    List<Tag> findTagsByBlogId(Integer blogId);

    void addATagWithBlog(Tag tag);

    void updateATagWithBlog(Tag tag);

    List<Tag> findTopTags();
}
