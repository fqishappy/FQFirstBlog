package com.fqishappy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fqishappy.domain.entity.Role;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author makejava
 * @since 2024-09-19 14:58:55
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 查询普通用户的角色权限
     * @param id
     * @return
     */
    List<String> selectRoleKeyByOtherUserId(Long id);

    /**
     * 改变角色状态
     * @param roleId
     * @param status
     */
    @Update("update sys_role set status = #{status} where id = #{roleId} ")
    void updateRoleStatus(Long roleId, String status);

    /**
     * 逻辑删除角色
     * @param id
     */
    @Update("update sys_role set del_flag = 1 where id = #{id} ")
    void deleteByIdLogic(Long id);
}
