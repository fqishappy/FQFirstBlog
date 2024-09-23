package com.fqishappy.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fqishappy.domain.entity.Category;
import org.apache.ibatis.annotations.Update;

/**
 * 分类表(Category)表数据库访问层
 *
 * @author makejava
 * @since 2024-09-13 09:31:31
 */
public interface CategoryMapper extends BaseMapper<Category> {
    /**
     * 逻辑删除分类
     * @param id
     */
    @Update("update fq_category set del_flag = 1 where id = #{id}")
    void deleteCategory(Long id);
}
