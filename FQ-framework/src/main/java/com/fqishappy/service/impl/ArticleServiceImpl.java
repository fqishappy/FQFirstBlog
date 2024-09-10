package com.fqishappy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqishappy.domain.entity.Article;
import com.fqishappy.mapper.ArticleMapper;
import com.fqishappy.service.ArticleService;
import org.springframework.stereotype.Service;

/**
 * @author fqishappy
 * @date 2024/9/10 18:39
 */

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
}
