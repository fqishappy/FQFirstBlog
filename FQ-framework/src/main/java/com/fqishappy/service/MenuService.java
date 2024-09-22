package com.fqishappy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.entity.Menu;
import com.fqishappy.domain.vo.MenuVO;

import java.util.List;

/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2024-09-19 14:50:43
 */
public interface MenuService extends IService<Menu> {
    /**
     * 查询用户的权限信息
     * @param id
     * @return
     */
    List<String> selectPermsByUserId(Long id);

    /**
     * 查询用户的路由信息
     * @param userId
     * @return
     */
    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    /**
     * 获取所有菜单
     * @param status
     * @param menuName
     * @return
     */
    ResponseResult getAllMenu(Integer status, String menuName);

    /**
     * 逻辑删除菜单
     * @param menuId
     * @return
     */
    ResponseResult deleteMenu(Long menuId);

    /**
     * 删除菜单-判断是否存在子菜单
     * @param menuId
     * @return
     */
    boolean hasChild(Long menuId);
}