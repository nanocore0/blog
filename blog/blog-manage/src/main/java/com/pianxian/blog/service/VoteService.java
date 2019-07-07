package com.pianxian.blog.service;

import com.pianxian.blog.entity.Vote;

public interface VoteService {

    int countVotes(Integer blogId);

    Vote findVoteByBlogIdAndUserId(Integer blogId, Integer userId);

    Vote saveOrUpdate(Vote vote);

    void deleteVote(Integer id);

    int countByUserId(Integer id);
}
