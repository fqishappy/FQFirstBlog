package com.fqishappy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fqishappy.domain.entity.Article;
import org.apache.ibatis.annotations.Update;

/**
 * @author fqishappy
 * @date 2024/9/10 18:33
 */
public interface ArticleMapper extends BaseMapper<Article> {
    /**
     * 逻辑删除
     * @param id
     */
    @Update("UPDATE fq_article SET del_flag = 1 WHERE id = #{id}")
    void deleteArticle(Long id);
}
