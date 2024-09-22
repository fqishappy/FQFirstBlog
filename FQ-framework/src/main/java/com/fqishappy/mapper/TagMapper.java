package com.fqishappy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fqishappy.domain.entity.Tag;
import org.apache.ibatis.annotations.Update;

/**
 * 标签(Tag)表数据库访问层
 *
 * @author makejava
 * @since 2024-09-19 01:10:19
 */
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 逻辑删除
     * @param tagId
     */
    @Update("update fq_tag set fq_tag.del_flag = 1 where id = #{tagId}")
    void logicDeleteTag(Long tagId);
}
