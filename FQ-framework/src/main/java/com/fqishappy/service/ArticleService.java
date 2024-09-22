package com.fqishappy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.dto.AddArticleDto;
import com.fqishappy.domain.dto.UpdateArticleDto;
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

    /**
     * 根据id查询文章内容
     * @param id
     * @return
     */
    ResponseResult getArticleDetail(Long id);

    /**
     * 更新redis中的浏览量
     * @param id
     * @return
     */
    ResponseResult updateViewCount(Long id);

    /**
     * 删除文章
     * @param id
     * @return
     */
    ResponseResult deleteByList(Long id);

    /**
     * 添加文章
     * @param article
     * @return
     */
    ResponseResult add(AddArticleDto article);

    /**
     * 文章列表模糊查询
     * @param pageNum
     * @param pageSize
     * @param title
     * @param summary
     * @return
     */
    ResponseResult getList(Integer pageNum, Integer pageSize, String title, String summary);

    /**
     * 后台查询文章内容详情
     * @param id
     * @return
     */
    ResponseResult getArticle(Long id);

    /**
     * 更新关系文章详情
     * @param article
     * @return
     */
    ResponseResult updateArticle(UpdateArticleDto article);
}

