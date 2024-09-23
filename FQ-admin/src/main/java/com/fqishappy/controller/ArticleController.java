package com.fqishappy.controller;

import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.dto.AddArticleDto;
import com.fqishappy.domain.dto.UpdateArticleDto;
import com.fqishappy.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author fqishappy
 * @date 2024/9/21 15:52
 */
@RestController
@RequestMapping("/content/article")
public class ArticleController {


    @Autowired
    private ArticleService articleService;

    /**
     * 新增文章
     * @param article
     * @return
     */
    @PostMapping
    public ResponseResult add(@RequestBody AddArticleDto article) {
        return articleService.add(article);
    }

    /**
     * 后台文章列表模糊查询
     * @return
     */
    @GetMapping("/list")
    public ResponseResult getList(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(required = false) String title,
                               @RequestParam(required = false) String summary) {

        return articleService.getList(pageNum, pageSize, title, summary);
    }

    /**
     * 查询文章详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult getArticle(@PathVariable Long id) {
        return articleService.getArticle(id);
    }


    /**
     * 更新文章详情
     * @param article
     * @return
     */
    @PutMapping
    public ResponseResult updateArticle(@RequestBody UpdateArticleDto article) {
        return articleService.updateArticle(article);
    }

    /**
     * 逻辑删除文章
     * @param ids
     * @return
     */
    @DeleteMapping
    public ResponseResult deleteByList(@RequestParam String ids) {
        String[] split = ids.split(",");
        for (String s : split) {
            articleService.deleteById(Long.valueOf(s));
        }
        return ResponseResult.okResult();
    }
}

