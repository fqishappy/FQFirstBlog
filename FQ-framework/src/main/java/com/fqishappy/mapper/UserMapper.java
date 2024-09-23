package com.fqishappy.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fqishappy.domain.entity.User;
import org.apache.ibatis.annotations.Update;

/**
 * 用户表(User)表数据库访问层
 *
 * @author makejava
 * @since 2024-09-13 17:15:37
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 逻辑删除用户
     * @param id
     */
    @Update("update sys_user set del_flag = 1 where id = #{id}")
    void logicDeleteById(Long id);

}
