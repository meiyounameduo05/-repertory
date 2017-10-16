package cn.itheima.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import cn.itheima.domain.Article;

public interface ArticleResiposty extends ElasticsearchRepository<Article, Integer> {

}
