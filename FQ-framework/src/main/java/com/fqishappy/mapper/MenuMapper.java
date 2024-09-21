package com.fqishappy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fqishappy.domain.entity.Menu;

import java.util.List;

/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2024-09-19 14:50:42
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 查询普通用户的权限信息
     * @param userId
     * @return
     */
    List<String> selectPermsByOtherUserId(Long userId);

    /**
     * 查询超级管理员的路由信息
     * @return
     */
    List<Menu> selectAllRouterMenu();

    /**
     * 查询普通用户的路由信息
     * @param userId
     * @return
     */
    List<Menu> selectOtherRouterMenuTreeByUserId(Long userId);
}