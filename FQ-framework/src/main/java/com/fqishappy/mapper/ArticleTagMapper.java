package com.fqishappy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fqishappy.domain.entity.ArticleTag;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author fqishappy
 * @date 2024/9/21 17:06
 */
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

    /**
     * 查询一对多，返回列表
     * @param articleId
     */
    @Select("select tag_id from fq_article_tag where article_id = #{articleId}")
    List<Integer> getArticleTagList(Long articleId);
}
