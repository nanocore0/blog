package com.pianxian.blog.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pianxian.blog.common.Response;
import com.pianxian.blog.entity.Tag;
import com.pianxian.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagController {

    @Autowired
    TagService tagService;

    @GetMapping("/tags/blog/{blogId}")
    @JsonView(Tag.TagView.class)
    public List<Tag> listTagsWithBlog(@PathVariable Integer blogId) {
        List<Tag> tags = tagService.findTagsByBlogId(blogId);
        return tags;
    }

    @GetMapping("/tags/top")
    @JsonView(Tag.TagView.class)
    public List<Tag> listTopTags() {
        return tagService.findTopTags();
    }

}
