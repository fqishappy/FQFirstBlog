package com.fqishappy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fqishappy.domain.entity.Role;

import java.util.List;

/**
 * 角色信息表(Role)表服务接口
 *
 * @author makejava
 * @since 2024-09-19 14:58:57
 */
public interface RoleService extends IService<Role> {

    /**
     * 查询用户的角色信息
     * @param id
     * @return
     */
    List<String> selectRoleKeyByUserId(Long id);
}