package com.pianxian.blog.mapper;

import com.pianxian.blog.entity.Tag;

public interface BlogTagMapper {

    int insertATagWithBlog(Tag tag); // 用到 Tag 的 id, blogId

    int deleteATagWithBlog(Tag tag); // Tag 需要id, blogId

    int deleteTagByBlogId(Integer blogId);

    int deleteTagByTagId(Integer tagId);

}