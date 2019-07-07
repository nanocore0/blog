package com.pianxian.blog.mapper;

import com.pianxian.blog.entity.Tag;

import java.util.List;

public interface TagMapper {

    Tag findTagById(Integer id);

    List<Tag> findTagsListByBlogId(Integer blogId);

    Tag findTagByName(String name);

    int insertTag(Tag tag);

    int deleteTag(Integer id);

    List<Tag> findTagsListOrderByBlogCountDesc();
}
