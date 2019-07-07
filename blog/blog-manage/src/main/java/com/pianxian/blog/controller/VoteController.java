package com.pianxian.blog.controller;

import com.pianxian.blog.entity.Vote;
import com.pianxian.blog.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class VoteController {

    @Autowired
    VoteService voteService;

    @GetMapping("/vote/exists")
    public Map<String, Object> exists(Vote vote) {
        Vote originVote = voteService.findVoteByBlogIdAndUserId(vote.getBlogId(), vote.getUserId());
        Map<String, Object> map = new HashMap<>();
        if (originVote != null) {
            map.put("exists", true);
            map.put("vote", originVote);
        } else {
            map.put("exists", false);
        }
        return map;
    }

    @PostMapping("/vote/add")
    public Vote vote(@RequestBody @Valid Vote vote) {
        if (vote.getStatus() != 1) {
            throw new IllegalArgumentException("参数异常");
        }
        vote = voteService.saveOrUpdate(vote);
        return vote;
    }

    @PutMapping("/vote/cancel")
    public Vote cancel(@RequestBody @Valid Vote vote) {
        if (vote.getStatus() != 0) {
            new IllegalArgumentException("参数异常");
        }
        voteService.saveOrUpdate(vote);
        return vote;
    }

    @DeleteMapping("/vote/{id}")
    public void deleteVote(@PathVariable("id") Integer id) {
        voteService.deleteVote(id);
    }
}
