package com.fqishappy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqishappy.domain.entity.RoleMenu;
import com.fqishappy.mapper.RoleMenuMapper;
import com.fqishappy.service.RoleMenuService;
import org.springframework.stereotype.Service;

/**
 * 角色和菜单关联表(RoleMenu)表服务实现类
 *
 * @author makejava
 * @since 2024-09-23 02:01:52
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}
