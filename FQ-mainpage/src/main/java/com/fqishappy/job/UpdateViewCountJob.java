package com.fqishappy.job;

import com.fqishappy.domain.entity.Article;
import com.fqishappy.service.ArticleService;
import com.fqishappy.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author fqishappy
 * @date 2024/9/18 16:54
 * 定时将redis中的浏览量数据更新到mysql中
 */
@Component
public class UpdateViewCountJob {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0 0/10 * * * ?")
    public void updateViewCount() {
        //获取redis中的数据
        Map<String, Integer> viewCountMap = redisCache.getCacheMap("article:ViewCount");
        List<Article> articles = viewCountMap.entrySet().stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());
        //update mysql
        articleService.updateBatchById(articles);
    }
}
