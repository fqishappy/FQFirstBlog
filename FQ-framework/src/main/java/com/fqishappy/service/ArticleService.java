package com.fqishappy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.entity.Article;

/**
 * @author fqishappy
 * @date 2024/9/10 18:38
 */
public interface ArticleService extends IService<Article> {
    /**
     * 返回热门文章
     * @return
     */
    ResponseResult hotArticleList();
}

