package com.fqishappy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fqishappy.domain.entity.ArticleTag;

import java.util.List;

/**
 * @author fqishappy
 * @date 2024/9/21 17:04
 */
public interface ArticleTagService extends IService<ArticleTag> {

    /**
     * 查找一对多关系
     * @param id
     * @return
     */
    List<String> getArticleTagList(Long id);

}
