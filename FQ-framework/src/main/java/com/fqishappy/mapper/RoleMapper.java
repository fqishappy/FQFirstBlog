package com.fqishappy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fqishappy.domain.entity.Role;

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
}
