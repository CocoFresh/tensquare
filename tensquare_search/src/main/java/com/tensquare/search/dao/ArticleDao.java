package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Auther: xintao.feng
 * @Date: 2019/10/14 14:30
 * @Description:
 */
public interface ArticleDao extends ElasticsearchRepository<Article, String> {
    public Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
