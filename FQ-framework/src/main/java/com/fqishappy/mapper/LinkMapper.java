package com.fqishappy.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fqishappy.domain.entity.Link;
import org.apache.ibatis.annotations.Update;

/**
 * 友链(Link)表数据库访问层
 *
 * @author makejava
 * @since 2024-09-13 16:43:35
 */
public interface LinkMapper extends BaseMapper<Link> {
    /**
     * 逻辑删除友链
     * @param id
     */
    @Update("update fq_link set del_flag = 1 where id = #{id}")
    void deleteLink(Long id);
}
