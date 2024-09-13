package com.fqishappy.controller;

import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.entity.Article;
import com.fqishappy.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fqishappy
 * @date 2024/9/10 18:41
 */

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /*@GetMapping("/list")
    public List<Article> test(){
        return articleService.list();
    }*/

    /**
     * 获取热门文章列表
     * @return
     */
    @GetMapping("/hotArticleList")
    public ResponseResult hosArticleList() {
        //查询热门文章 封装成ResponseResult返回
        ResponseResult responseResult = articleService.hotArticleList();
        System.out.println(responseResult);
        return responseResult;
    }

    /**
     * 获取文章列表
     * @return
     */
    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        return articleService.articleList(pageNum,pageSize,categoryId);
    }

    /**
     * 根据文章id查询内容
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable Long id) {
        System.out.println(id);
        return articleService.getArticleDetail(id);
    }
}
