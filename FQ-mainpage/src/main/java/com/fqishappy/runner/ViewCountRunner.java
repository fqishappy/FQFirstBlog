package com.fqishappy.runner;

import com.fqishappy.domain.entity.Article;
import com.fqishappy.mapper.ArticleMapper;
import com.fqishappy.utils.RedisCache;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author fqishappy
 * @date 2024/9/18 16:50
 * 用于项目启动时redis从数据库里读取浏览量信息
 */
@Component
public class ViewCountRunner implements CommandLineRunner {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        //查询博客信息
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(article -> article.getId().toString(), article -> article.getViewCount().intValue()));
        //存储到redis内
        redisCache.setCacheMap("article:ViewCount", viewCountMap);
    }
}
