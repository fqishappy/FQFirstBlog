package com.fqishappy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.dto.RoleDto;
import com.fqishappy.domain.dto.RoleStatusDto;
import com.fqishappy.domain.dto.UpdateRoleDto;
import com.fqishappy.domain.entity.Role;
import com.fqishappy.domain.entity.RoleMenu;
import com.fqishappy.domain.vo.PageVO;
import com.fqishappy.domain.vo.RoleListVO;
import com.fqishappy.domain.vo.RoleVO;
import com.fqishappy.domain.vo.UpdateRoleVO;
import com.fqishappy.mapper.RoleMapper;
import com.fqishappy.mapper.RoleMenuMapper;
import com.fqishappy.service.RoleService;

import com.fqishappy.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author makejava
 * @since 2024-09-19 14:58:57
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    /**
     * 查询用户的角色信息
     * @param id
     * @return
     */
    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        //判断是否是管理员，如果是，就返回集合中只需要有admin
        if(id == 1L){
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }

        //否则查询对应普通用户所具有的的角色信息
        return getBaseMapper().selectRoleKeyByOtherUserId(id);
    }

    /**
     * 分页模糊查询角色列表
     * @param pageNum
     * @param pageSize
     * @param roleName
     * @param status
     * @return
     */
    @Override
    public ResponseResult getRoleList(Integer pageNum, Integer pageSize, String roleName, String status) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(roleName),Role::getRoleName, roleName).eq(StringUtils.hasText(status), Role::getStatus, status);
        Page<Role> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);
        List<Role> records = page.getRecords();
        List<RoleVO> roleVOS = BeanCopyUtils.copyBeanList(records, RoleVO.class);
        return ResponseResult.okResult(new PageVO(roleVOS, page.getTotal()));
    }

    /**
     * 更新角色状态
     * @param roleStatusDto
     * @return
     */
    @Override
    public ResponseResult updateRoleStatus(RoleStatusDto roleStatusDto) {
        Role byId = getById(roleStatusDto.getRoleId());
        byId.setStatus(roleStatusDto.getStatus());
        roleMapper.updateById(byId);
        return ResponseResult.okResult();
    }

    /**
     * 新增角色
     * @param roleDto
     * @return
     */
    @Override
    @Transactional
    public ResponseResult addRole(RoleDto roleDto) {
        if (roleDto.getMenuIds() != null && !roleDto.getMenuIds().isEmpty()) {
            Role role = BeanCopyUtils.copyBean(roleDto, Role.class);
            roleMapper.insert(role);
            addRoleMenu(roleDto.getRoleName(), roleDto.getMenuIds());
            return ResponseResult.okResult();
        }
        return ResponseResult.errorResult(500, "禁止传递无菜单角色");
    }

    /**
     * 关联角色权限
     * @param roleName
     * @param menuIds
     */
    private void addRoleMenu(String roleName, List<Long> menuIds) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getRoleName, roleName);
        Role one = getOne(queryWrapper);
        Long id = one.getId();
        for (Long menuId : menuIds) {
            roleMenuMapper.insert(new RoleMenu(id, menuId));
        }
    }

    /**
     * 查询角色详情
     * @param id
     * @return
     */
    @Override
    public ResponseResult getRole(Long id) {
        Role byId = getById(id);
        return ResponseResult.okResult(BeanCopyUtils.copyBean(byId, UpdateRoleVO.class));
    }

    /**
     * 更新角色详情
     * @param role
     * @return
     */
    @Override
    public ResponseResult updateRole(UpdateRoleDto role) {
        Long id = role.getId();
        roleMapper.updateById(BeanCopyUtils.copyBean(role, Role.class));
        List<Long> menuIds = role.getMenuIds();
        LambdaQueryWrapper<RoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleMenu::getRoleId, id);
        roleMenuMapper.delete(queryWrapper);
        for (Long menuId : menuIds) {
            roleMenuMapper.insert(new RoleMenu(id, menuId));
        }
        return ResponseResult.okResult();
    }

    /**
     * 逻辑删除角色
     * @param id
     * @return
     */
    @Override
    public void deleteById(Long id) {
        roleMapper.deleteByIdLogic(id);
    }

    /**
     * 查询所有状态正常的角色
     * @return
     */
    @Override
    public ResponseResult getAllRole() {
        List<Role> list = list();
        return ResponseResult.okResult(BeanCopyUtils.copyBeanList(list, RoleListVO.class));
    }


}