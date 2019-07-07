package com.pianxian.blog.service;

import com.pianxian.blog.entity.EsBlog;
import com.pianxian.blog.entity.Tag;
import com.pianxian.blog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * EsBlog 服务接口
 *
 */
public interface EsBlogService {

	/**
	 * 删除EsBLog
	 * @param id
	 */
	void deleteEsBlog(String id);
	
	/**
	 * 更新EsBlog
	 * @param esBlog
	 * @return
	 */
	EsBlog updateEsBlog(EsBlog esBlog);
	
	/**
	 * 根据Blog的id获取EsBlog
	 * @param blogId
	 * @return
	 */
	EsBlog findEsBlogByBlogId(Long blogId);
	
	/**
	 * 最新博客列表
	 * @param keyword
	 * @param pageable
	 * @return
	 */
	Page<EsBlog> listNewestEsBlogs(String keyword, Pageable pageable);
	
	/**
	 * 最热博客列表
	 * @param keyword
	 * @param pageable
	 * @return
	 */
	Page<EsBlog> listHotestEsBlogs(String keyword, Pageable pageable);
	
	/**
	 * 所有博客列表
	 * @param pageable
	 * @return
	 */
	Page<EsBlog> listEsBlogs(Pageable pageable);
	
	/**
	 * 最新前5
	 * @return
	 */
	List<EsBlog> listTop5NewestEsBlogs();
	
	/**
	 * 最热前5
	 * @return
	 */
	List<EsBlog> listTop5HotestEsBlogs();
	
	/**
	 * 最热前10标签
	 * @return
	 */
	List<Tag> listTop10Tags();
	
	/**
	 * 最热前9用户
	 * @return
	 */
	List<User> listTop10Users();
}
