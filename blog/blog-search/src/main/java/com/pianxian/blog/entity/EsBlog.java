package com.pianxian.blog.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Document(indexName = "blog", type = "blog")
public class EsBlog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    @Field(type= FieldType.Long)
    private Long blogId;
    @Field(type=FieldType.Text)
    private String title;
    @Field(type=FieldType.Text)
    private String summary;
    @Field(type=FieldType.Text)
    private String content;
    private Integer userId;
    private Integer catalogId;
    private LocalDateTime createTime;
    private LocalDateTime lastEditTime;
    private Integer readSize;
    private Integer commentSize;
    private Integer voteSize;
    @Field(type=FieldType.Text, fielddata=true)
    private String author;
    @Field(type = FieldType.Object)
    private List<String> tags;

    protected EsBlog() {
    }

    public EsBlog(Long blogId, String title, String summary, String content, Integer userId, Integer catalogId, LocalDateTime createTime, LocalDateTime lastEditTime, Integer readSize, Integer commentSize, Integer voteSize, String author, List<String> tags) {
        this.blogId = blogId;
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.userId = userId;
        this.catalogId = catalogId;
        this.createTime = createTime;
        this.lastEditTime = lastEditTime;
        this.readSize = readSize;
        this.commentSize = commentSize;
        this.voteSize = voteSize;
        this.author = author;
        this.tags = tags;
    }

    public EsBlog(Blog blog) {
        this.blogId = blog.getId().longValue();
        this.title = blog.getTitle();
        this.summary = blog.getSummary();
        this.content = blog.getContent();
        this.userId = blog.getUserId();
        this.catalogId = blog.getCatalogId();
        this.createTime = blog.getCreateTime();
        this.lastEditTime = blog.getLastEditTime();
        this.readSize = blog.getReadSize();
        this.commentSize = blog.getCommentSize();
        this.voteSize = blog.getVoteSize();
        this.author = blog.getAuthor();
        this.tags = blog.getTags().stream().map(Tag :: getName).collect(Collectors.toList());
    }

    public void update(Blog blog) {
        this.blogId = blog.getId().longValue();
        this.title = blog.getTitle();
        this.summary = blog.getSummary();
        this.content = blog.getContent();
        this.userId = blog.getUserId();
        this.catalogId = blog.getCatalogId();
        this.createTime = blog.getCreateTime();
        this.lastEditTime = blog.getLastEditTime();
        this.readSize = blog.getReadSize();
        this.commentSize = blog.getCommentSize();
        this.voteSize = blog.getVoteSize();
        this.author = blog.getAuthor();
        this.tags = blog.getTags().stream().map(Tag :: getName).collect(Collectors.toList());
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(LocalDateTime lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Integer getReadSize() {
        return readSize;
    }

    public void setReadSize(Integer readSize) {
        this.readSize = readSize;
    }

    public Integer getCommentSize() {
        return commentSize;
    }

    public void setCommentSize(Integer commentSize) {
        this.commentSize = commentSize;
    }

    public Integer getVoteSize() {
        return voteSize;
    }

    public void setVoteSize(Integer voteSize) {
        this.voteSize = voteSize;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
