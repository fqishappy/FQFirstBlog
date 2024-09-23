package com.fqishappy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqishappy.constants.SystemConstants;
import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.dto.AddArticleDto;
import com.fqishappy.domain.dto.UpdateArticleDto;
import com.fqishappy.domain.entity.Article;
import com.fqishappy.domain.entity.ArticleTag;
import com.fqishappy.domain.entity.Category;
import com.fqishappy.domain.entity.Tag;
import com.fqishappy.domain.vo.*;
import com.fqishappy.mapper.ArticleMapper;
import com.fqishappy.mapper.ArticleTagMapper;
import com.fqishappy.service.ArticleService;
import com.fqishappy.service.ArticleTagService;
import com.fqishappy.service.CategoryService;
import com.fqishappy.utils.BeanCopyUtils;

import com.fqishappy.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author fqishappy
 * @date 2024/9/10 18:39
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {



    @Autowired
    @Lazy
    private CategoryService categoryService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private ArticleMapper articleMapper;


    /**
     * 查询热门文章列表
     * @return
     */
    @Override
    public ResponseResult hotArticleList() {
        //查询热门文章 封装ResponseResult返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //正式文章 非草稿 且按照浏览量排序
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL).orderByDesc(Article::getViewCount);
        Page<Article> page = new Page(1, 10);

        page(page, queryWrapper);

        List<Article> articleList = page.getRecords();

        articleList.forEach(article -> {
            Long id = article.getId();
            Long viewCount = Long.valueOf(redisCache.getCacheMapValue(SystemConstants.ARTICLE_VIEW_COUNT, id.toString()).toString());
            article.setViewCount(viewCount);
        });
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

    /**
     * 查询文章列表
     * @param pageNum 页码
     * @param pageSize 每页查询数量
     * @param categoryId 文章分类id
     * @return
     */
    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询条件
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //如果有categoryId，查询时要和传入的相同
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, Article::getCategoryId, categoryId);
        //是否正式发布
        lambdaQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //对isTop进行降序
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);
        //分页查询
        Page<Article> articlePage = new Page(pageNum, pageSize);
        page(articlePage, lambdaQueryWrapper);
        //查询categoryName
        List<Article> articles = articlePage.getRecords();


        //articleid查询name
        /*for (Article article : records) {
            Category byId = categoryService.getById(article.getCategoryId());
            article.setCategoryName(byId.getName());
        }*/

        //由article对象获取分类id，再由id查询分类表中的name
        //查询文章id，并从redis读取浏览量
        articles.forEach(article -> {
                    article.setCategoryName(
                            categoryService.getById(article.getCategoryId()).getName());
                    Long id = article.getId();
                    Long viewCount = Long.valueOf(redisCache.getCacheMapValue(SystemConstants.ARTICLE_VIEW_COUNT, id.toString()).toString());
                    article.setViewCount(viewCount);
                }
        );
        //封装查询结果
        List<ArticleListVO> articleListVOS = BeanCopyUtils.copyBeanList(articles, ArticleListVO.class);

        return ResponseResult.okResult(new PageVO(articleListVOS, articlePage.getTotal()));


    }

    /**
     * 根据id查询文章内容
     * @param id
     * @return
     */
    @Override
    public ResponseResult getArticleDetail(Long id) {
        Article article = getById(id);
        Long viewCount = Long.valueOf(redisCache.getCacheMapValue(SystemConstants.ARTICLE_VIEW_COUNT, id.toString()).toString());
        ArticleDetailVO articleDetailVO = BeanCopyUtils.copyBean(article, ArticleDetailVO.class);
        Category category = categoryService.getById(articleDetailVO.getCategoryId());
        if (category != null) {
            articleDetailVO.setCategoryName(category.getName());
        }
        articleDetailVO.setViewCount(viewCount);
        return ResponseResult.okResult(articleDetailVO);
    }

    /**
     * 更新redis中的浏览量
     * @param id
     * @return
     */
    @Override
    public ResponseResult updateViewCount(Long id) {
        redisCache.incrementCacheMapValue(SystemConstants.ARTICLE_VIEW_COUNT, id.toString(), 1);
        return ResponseResult.okResult();

    }

    /**
     * 批量删除文章
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteById(Long id) {
        articleMapper.deleteArticle(id);
        return ResponseResult.okResult();
    }

    /**
     * 添加文章
     * @param articleDto
     * @return
     */

    @Override
    @Transactional
    public ResponseResult add(AddArticleDto articleDto) {
        //添加 博客
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        save(article);
        List<ArticleTag> articleTags = articleDto.getTags().stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());
        //添加 博客和标签的关联
        articleTagService.saveBatch(articleTags);
        return ResponseResult.okResult();
    }

    /**
     * 后台文章列表模糊查询
     * @param pageNum
     * @param pageSize
     * @param title
     * @param summary
     * @return
     */
    @Override
    public ResponseResult<PageVO> getList(Integer pageNum, Integer pageSize, String title, String summary) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(title),Article::getTitle, title)
                .like(StringUtils.hasText(summary), Article::getSummary, summary);
        Page<Article> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, wrapper);
        List<Article> articles = page.getRecords();
        System.out.println(articles);
        return ResponseResult.okResult(new PageVO(
                BeanCopyUtils.copyBeanList(articles, ArticleAdminListVo.class),
                page.getTotal()
        ));
    }

    /**
     * 后台查询文章内容详情
     * @param id
     * @return
     */
    @Override
    public ResponseResult getArticle(Long id) {
        ArticleAdminVO articleAdminVO = BeanCopyUtils.copyBean(getById(id), ArticleAdminVO.class);
        articleAdminVO.setTags(articleTagService.getArticleTagList(id));
        return ResponseResult.okResult(articleAdminVO);
    }

    /**
     * 更新文章详情
     * @param article
     * @return
     */
    @Override
    public ResponseResult updateArticle(UpdateArticleDto article) {
        Article articleCopy = BeanCopyUtils.copyBean(article, Article.class);
        updateById(articleCopy);
        //删除原有标签和博客的关联
        LambdaQueryWrapper<ArticleTag> articleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleTagLambdaQueryWrapper.eq(ArticleTag::getArticleId,article.getId());
        articleTagService.remove(articleTagLambdaQueryWrapper);
        //添加 博客和标签的关联
        List<ArticleTag> articleTags = article.getTags().stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());
        articleTagService.saveBatch(articleTags);
        return ResponseResult.okResult();
    }
}
