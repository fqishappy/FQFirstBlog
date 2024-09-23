package com.fqishappy.controller;

import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.dto.RoleDto;
import com.fqishappy.domain.dto.RoleStatusDto;
import com.fqishappy.domain.dto.UpdateRoleDto;
import com.fqishappy.domain.vo.RoleVO;
import com.fqishappy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author fqishappy
 * @date 2024/9/22 18:21
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 分页模糊查询角色列表
     * @param pageNum
     * @param pageSize
     * @param roleName
     * @param status
     * @return
     */
    @GetMapping("/list")
    public ResponseResult getRoleList(@RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize,
                                      @RequestParam(required = false) String roleName,
                                      @RequestParam(required = false) String status) {
        return roleService.getRoleList(pageNum, pageSize, roleName, status);
    }

    /**
     * 改变角色状态
     * @param roleStatusDto
     * @return
     */
    @PutMapping("/changeStatus")
    public ResponseResult updateRoleStatus(@RequestBody RoleStatusDto roleStatusDto) {
        return roleService.updateRoleStatus(roleStatusDto);
    }

    /**
     * 新增角色
     * @param role
     * @return
     */
    @PostMapping
    public ResponseResult addRole(@RequestBody RoleDto role) {
        return roleService.addRole(role);
    }

    /**
     * 查询角色详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult getrole(@PathVariable Long id) {
        return roleService.getRole(id);
    }

    /**
     * 更新角色详情
     * @param role
     * @return
     */
    @PutMapping
    public ResponseResult updaterole(@RequestBody UpdateRoleDto role) {
        return roleService.updateRole(role);
    }

    /**
     * 逻辑删除角色
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public ResponseResult deleteByList(@RequestParam String ids) {
        String[] split = ids.split(",");
        for (String s : split) {
            roleService.deleteById(Long.valueOf(s));
        }
        return ResponseResult.okResult();
    }
}
