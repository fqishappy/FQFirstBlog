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

    /**
     * 根据文章分类id进行分页查询
     * @param pageNum 页码
     * @param pageSize 每页查询数量
     * @param categoryId 文章分类id
     * @return
     */
    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);
}

