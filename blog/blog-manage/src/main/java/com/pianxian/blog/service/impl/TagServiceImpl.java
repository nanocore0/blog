package com.pianxian.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.pianxian.blog.entity.Tag;
import com.pianxian.blog.mapper.BlogTagMapper;
import com.pianxian.blog.mapper.TagMapper;
import com.pianxian.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagMapper tagMapper;

    @Autowired
    BlogTagMapper blogTagMapper;

    @Override
    public void save(Tag tag) {
        Tag tagByName = tagMapper.findTagByName(tag.getName());
        if (tagByName != null) {
            tagMapper.insertTag(tag);
        }
    }

    @Override
    public void delete(Integer id) {
        tagMapper.deleteTag(id);
    }

    @Override
    public List<Tag> findTagsByBlogId(Integer blogId) {
        return tagMapper.findTagsListByBlogId(blogId);
    }

    @Override
    @Transactional
    public void addATagWithBlog(Tag tag) {
        // 查询标签名是否已经被一个标签使用了，如果已存在则使用这个标签，如果不存在则新建一个
        Tag originTag = tagMapper.findTagByName(tag.getName());
        if(originTag == null) {
            originTag = new Tag();
            originTag.setName(tag.getName());
            tagMapper.insertTag(originTag);
        }
        // 和新的标签建立关系
        originTag.setBlogId(tag.getBlogId());
        blogTagMapper.insertATagWithBlog(originTag);
    }

    @Override
    @Transactional
    public void updateATagWithBlog(Tag tag) {
        // 删除原来的关系
        blogTagMapper.deleteATagWithBlog(tag);

        // 建立新的关系
        Tag originTag = tagMapper.findTagByName(tag.getName());
        if(originTag == null) {
            originTag = new Tag();
            originTag.setName(tag.getName());
            tagMapper.insertTag(originTag);
        }
        originTag.setBlogId(tag.getBlogId());
        blogTagMapper.insertATagWithBlog(originTag);
    }

    @Override
    public List<Tag> findTopTags() {
        PageHelper.startPage(0, 15);
        List<Tag> tags = tagMapper.findTagsListOrderByBlogCountDesc();
        return tags;
    }
}
