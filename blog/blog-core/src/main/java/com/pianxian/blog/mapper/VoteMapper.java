package com.pianxian.blog.mapper;

import com.pianxian.blog.entity.Vote;

import java.util.List;

public interface VoteMapper {

    Vote findVoteById(Integer id);

    int insertVote(Vote vote);

    int updateVote(Vote vote);

    int deleteVote(Integer id);

    int deleteVoteByBlogId(Integer blogId);

    int deleteVoteByUserId(Integer userId);

    List<Vote> findVotesListByBlogId(Integer blogId);

    Vote findVoteByBlogIdAndUserId(Vote vote);

    int countVotesByBlogId(Integer blogId);

    int countVotesByUserId(Integer userId);
}
