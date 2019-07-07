package com.pianxian.blog.service.impl;

import com.pianxian.blog.entity.Blog;
import com.pianxian.blog.entity.Vote;
import com.pianxian.blog.mapper.BlogMapper;
import com.pianxian.blog.mapper.VoteMapper;
import com.pianxian.blog.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    VoteMapper voteMapper;

    @Autowired
    BlogMapper blogMapper;

    @Override
    public int countVotes(Integer blogId) {
        return voteMapper.countVotesByBlogId(blogId);
    }

    @Override
    public Vote findVoteByBlogIdAndUserId(Integer blogId, Integer userId) {
        Vote vote = new Vote();
        vote.setUserId(userId);
        vote.setBlogId(blogId);
        return voteMapper.findVoteByBlogIdAndUserId(vote);
    }

    @Override
    @Transactional
    public Vote saveOrUpdate(Vote vote) { {
        Vote oldVote = voteMapper.findVoteByBlogIdAndUserId(vote);
        if (oldVote != null) {
            oldVote.setStatus(vote.getStatus());
            voteMapper.updateVote(oldVote);
        } else {
            voteMapper.insertVote(vote);
        }
        // 更新博客点赞数量
        Blog blog = blogMapper.findBlogById(vote.getBlogId());
        int voteSize = voteMapper.countVotesByBlogId(vote.getBlogId());
        blog.setVoteSize(voteSize);
        blogMapper.updateBlog(blog);
        // 主键返回
        return vote;
    }}

    @Override
    @Transactional
    public void deleteVote(Integer id) {
        Vote vote = voteMapper.findVoteById(id);
        Blog blog = blogMapper.findBlogById(vote.getBlogId());
        blog.setVoteSize(blog.getVoteSize() - 1);
        blogMapper.updateBlog(blog);
        voteMapper.deleteVote(id);
    }

    @Override
    public int countByUserId(Integer id) {
        return voteMapper.countVotesByUserId(id);
    }
}
