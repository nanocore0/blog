package com.pianxian.blog.repository;

import com.pianxian.blog.entity.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsBlogRepository extends ElasticsearchRepository<EsBlog, String> {

    /**
     * 模糊查询(去重)
     * @param title
     * @param summary
     * @param content
     * @return
     */
    Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContaining(
            String title, String summary, String content, String tag, Pageable pageable);

    /**
     * 根据Blog的id查询EsBlog
     * @param blogId
     * @return
     */
    EsBlog findEsBlogByBlogId(Long blogId);
}
