package com.pianxian.blog.entity;

import com.fasterxml.jackson.annotation.JsonView;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

public class Blog {
    public interface BlogListView {}; // 博客列表
    public interface BlogDetailView extends BlogListView {} ; // 博客详情

    private Integer id;
    @NotBlank
    private String title;
    @NotBlank
    private String summary;
    @Min(1)
    private Integer userId;
    private LocalDateTime createTime;
    private LocalDateTime lastEditTime;
    private Integer readSize;
    private Integer commentSize;
    private Integer voteSize;
    @Min(1)
    private Integer catalogId;
    @NotBlank
    private String content;
    @NotBlank
    private String htmlContent;

    private String author; // 作者
    private List<Tag> tags; // 接收博客的标签

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @JsonView(BlogListView.class)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @JsonView(BlogListView.class)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonView(BlogListView.class)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    @JsonView(BlogListView.class)
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    @JsonView(BlogListView.class)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JsonView(BlogListView.class)
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @JsonView(BlogListView.class)
    public LocalDateTime getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(LocalDateTime lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    @JsonView(BlogListView.class)
    public Integer getReadSize() {
        return readSize;
    }

    public void setReadSize(Integer readSize) {
        this.readSize = readSize;
    }

    @JsonView(BlogListView.class)
    public Integer getCommentSize() {
        return commentSize;
    }

    public void setCommentSize(Integer commentSize) {
        this.commentSize = commentSize;
    }

    @JsonView(BlogListView.class)
    public Integer getVoteSize() {
        return voteSize;
    }

    public void setVoteSize(Integer voteSize) {
        this.voteSize = voteSize;
    }

    @JsonView(BlogListView.class)
    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }

    @JsonView(BlogDetailView.class)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @JsonView(BlogDetailView.class)
    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent == null ? null : htmlContent.trim();
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", userId=" + userId +
                ", author='" + author + '\'' +
                ", createTime=" + createTime +
                ", readSize=" + readSize +
                ", commentSize=" + commentSize +
                ", voteSize=" + voteSize +
                ", catalogId=" + catalogId +
                ", content='" + content + '\'' +
                ", htmlContent='" + htmlContent + '\'' +
                '}';
    }
}