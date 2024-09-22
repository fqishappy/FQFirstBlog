package com.fqishappy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqishappy.domain.entity.ArticleTag;
import com.fqishappy.mapper.ArticleTagMapper;
import com.fqishappy.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fqishappy
 * @date 2024/9/21 17:05
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService{

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Override
    public List<String> getArticleTagList(Long articleId) {
        List<Integer> tags = articleTagMapper.getArticleTagList(articleId);
        return tags.stream().map(Object::toString).collect(Collectors.toList());
    }
}
