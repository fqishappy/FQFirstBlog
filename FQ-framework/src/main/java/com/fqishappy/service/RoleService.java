package com.fqishappy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.dto.RoleDto;
import com.fqishappy.domain.dto.RoleStatusDto;
import com.fqishappy.domain.dto.UpdateRoleDto;
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

    /**
     * 分页模糊查询角色列表
     * @param pageNum
     * @param pageSize
     * @param roleName
     * @param status
     * @return
     */
    ResponseResult getRoleList(Integer pageNum, Integer pageSize, String roleName, String status);

    /**
     * 改变角色状态
     * @param roleStatusDto
     * @return
     */
    ResponseResult updateRoleStatus(RoleStatusDto roleStatusDto);

    /**
     * 新增角色
     * @param role
     * @return
     */
    ResponseResult addRole(RoleDto role);

    /**
     * 查询角色详情
     * @param id
     * @return
     */
    ResponseResult getRole(Long id);

    /**
     * 更新角色详情
     * @param role
     * @return
     */
    ResponseResult updateRole(UpdateRoleDto role);

    /**
     * 逻辑删除角色
     * @param id
     * @return
     */
    void deleteById(Long id);
}