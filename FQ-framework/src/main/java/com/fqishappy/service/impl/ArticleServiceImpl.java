package com.fqishappy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqishappy.constants.SystemConstants;
import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.entity.Article;
import com.fqishappy.domain.vo.HotArticleVO;
import com.fqishappy.mapper.ArticleMapper;
import com.fqishappy.service.ArticleService;
import com.fqishappy.utils.BeanCopyUtils;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fqishappy
 * @date 2024/9/10 18:39
 */

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Override
    public ResponseResult hotArticleList() {
        //查询热门文章 封装ResponseResult返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //正式文章 非草稿 且按照浏览量排序
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL).orderByDesc(Article::getViewCount);
        Page<Article> page = new Page(1, 10);

        page(page, queryWrapper);

        List<Article> articleList = page.getRecords();

        //Bean拷贝

        /*
        List<HotArticleVO> hotArticleVOList = new ArrayList<>();
        for (Article article : articleList) {
            HotArticleVO hotArticleVO = new HotArticleVO();
            BeanUtils.copyProperties(article, hotArticleVO);
            hotArticleVOList.add(hotArticleVO);
        }*/
        List<HotArticleVO> ts = BeanCopyUtils.copyBeanList(articleList, HotArticleVO.class);
        return ResponseResult.okResult(articleList);
    }
}
