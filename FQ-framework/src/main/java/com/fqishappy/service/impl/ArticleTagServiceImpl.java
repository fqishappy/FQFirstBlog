package com.fqishappy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqishappy.domain.entity.ArticleTag;
import com.fqishappy.mapper.ArticleTagMapper;
import com.fqishappy.service.ArticleTagService;
import org.springframework.stereotype.Service;

/**
 * @author fqishappy
 * @date 2024/9/21 17:05
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService{
}
