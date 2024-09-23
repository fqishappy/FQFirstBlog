package com.fqishappy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqishappy.constants.SystemConstants;
import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.entity.Menu;
import com.fqishappy.domain.entity.RoleMenu;
import com.fqishappy.domain.vo.MenuTreeVO;
import com.fqishappy.domain.vo.UpdateMenuTreeVO;
import com.fqishappy.mapper.MenuMapper;
import com.fqishappy.mapper.RoleMenuMapper;
import com.fqishappy.service.MenuService;
import com.fqishappy.service.RoleMenuService;
import com.fqishappy.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2024-09-19 14:50:44
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMenuService roleMenuService;

    /**
     * 根据id查询用户权限关键字
     * @param id
     * @return
     */
    @Override
    //查询用户的权限信息
    public List<String> selectPermsByUserId(Long id) {
        //根据用户id查询用户的权限信息。用户id为id代表管理员，如果是管理员就返回所有的权限
        if(id == 1L){
            LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
            //查询条件是permissions中需要有所有菜单类型为C或者F的权限。SystemCanstants是我们在huanf-framework工程写的类
            wrapper.in(Menu::getMenuType, SystemConstants.TYPE_MENU, SystemConstants.TYPE_BUTTON);
            //查询条件是permissions中需要有状态为正常的权限。SystemCanstants是我们在huanf-framework工程写的类
            wrapper.eq(Menu::getStatus,SystemConstants.STATUS_NORMAL);
            //查询条件是permissions中需要未被删除的权限的权限
            List<Menu> menus = list(wrapper);
            List<String> perms = menus.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
            return perms;
        }

        //如果不是管理员就返回对应用户所具有的权限
        List<String> otherPerms = getBaseMapper().selectPermsByOtherUserId(id);
        return otherPerms;
    }

    /**
     * 根据id查询权限树
     * @param userId
     * @return
     */
    @Override
    public List<Menu> selectRouterMenuTreeByUserId(Long userId) {
        MenuMapper menuMapper = getBaseMapper();

        List<Menu> menus = null;

        //判断是否是超级管理员，用户id为id代表超级管理员，如果是就返回所有符合要求的权限菜单
        if(userId.equals(1L)){
            menus = menuMapper.selectAllRouterMenu();
        }else{
            //如果不是超级管理员，就查询对应用户所具有的路由信息(权限菜单)
            menus = menuMapper.selectOtherRouterMenuTreeByUserId(userId);
        }

        //构建成tree，也就是子父菜单树，有层级关系
        //思路:先找出第一层的菜单，然后再找子菜单(也就是第二层)，把子菜单的结果赋值给Menu类的children字段
        List<Menu> menuTree = buildMenuTree(menus,0L);

        return menuTree;
    }

    /**
     * 获取所有菜单
     * @param status
     * @param menuName
     * @return
     */
    @Override
    public ResponseResult getAllMenu(Integer status, String menuName) {
        return ResponseResult.okResult(list());
    }

    /**
     * 逻辑删除菜单
     * @param menuId
     * @return
     */
    @Override
    public ResponseResult deleteMenu(Long menuId) {
        menuMapper.deleteMenu(menuId);
        return ResponseResult.okResult();
    }


    //用于把List集合里面的数据构建成tree，也就是子父菜单树，有层级关系
    private List<Menu> buildMenuTree(List<Menu> menus, long parentId) {
        return menus.stream()
                //过滤找出父菜单树，也就是第一层
                .filter(menu -> menu.getParentId().equals(parentId))
                //xxgetChildren是我们在下面写的方法，用于获取子菜单的List集合
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());
    }

    /**
     * 获取存入参数的子menu集合
     * @param menu
     * @return
     */
    private List<Menu> getChildren(Menu menu, List<Menu> menus) {
        return menus.stream()
                //通过过滤得到子菜单
                .filter(m -> m.getParentId().equals(menu.getId()))
                //如果有三层菜单的话，也就是子菜单的子菜单，我们就用下面那行递归(自己调用自己)来处理
                .map(m -> m.setChildren(getChildren(m,menus)))
                .collect(Collectors.toList());
    }

    /**
     * 删除菜单-判断是否存在子菜单
     * @param menuId
     * @return
     */
    @Override
    public boolean hasChild(Long menuId) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Menu::getParentId,menuId);
        return count(queryWrapper) != 0;
    }

    /**
     * 获取菜单树接口
     * @return
     */
    @Override
    public List<MenuTreeVO> treeSelect() {
        // 查询根菜单
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Menu::getParentId, SystemConstants.ROOT_ROLE);
        List<Menu> rootMenus = list(queryWrapper);

        // 将根菜单转换为 MenuTreeVO
        List<MenuTreeVO> menus = BeanCopyUtils.copyBeanList(rootMenus, MenuTreeVO.class);

        // 为每个根菜单递归获取子节点
        for (MenuTreeVO menu : menus) {
            List<MenuTreeVO> children = getChildrenRecursively(menu.getId());
            menu.setChildren(children);
            menu.setLabel(menu.getMenuName());
        }

        return menus;
    }

    /**
     * 查询角色菜单列表树
     * @param id
     * @return
     */
    @Override
    public ResponseResult getRoleMenuTreeSelect(Long id) {
        /*LambdaQueryWrapper<RoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleMenu::getRoleId, id);
        List<RoleMenu> roleMenus = roleMenuService.list(queryWrapper);
        List<Menu> menus = new ArrayList<>();
        for (RoleMenu roleMenu : roleMenus) {
            menus.add(getById(roleMenu.getMenuId()));
        }
        List<MenuTreeVO> menuTreeVOs = BeanCopyUtils.copyBeanList(menus, MenuTreeVO.class);
        for (MenuTreeVO menuTreeVO : menuTreeVOs) {
            List<MenuTreeVO> children = getChildrenRecursively(menuTreeVO.getId());
            menuTreeVO.setChildren(children);
            menuTreeVO.setLabel(menuTreeVO.getMenuName());
        }*/
        LambdaQueryWrapper<RoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleMenu::getRoleId, id);
        List<RoleMenu> roleMenus = roleMenuService.list(queryWrapper);
        List<Long> menuIds = roleMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        List<MenuTreeVO> menuTreeVOs = treeSelect();
        return ResponseResult.okResult(new UpdateMenuTreeVO(menuTreeVOs, menuIds));
    }

    /**
     * 递归获取子菜单，并判断是否为叶子节点
     * @param parentId
     * @return
     */
    public List<MenuTreeVO> getChildrenRecursively(Long parentId) {
        // 查询该父节点的所有子菜单
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Menu::getParentId, parentId);
        List<Menu> menuList = list(queryWrapper);

        // 将查询到的菜单转换为 MenuTreeVO
        List<MenuTreeVO> children = BeanCopyUtils.copyBeanList(menuList, MenuTreeVO.class);

        // 为每个子菜单递归查找它的子节点
        for (MenuTreeVO child : children) {
            List<MenuTreeVO> subChildren = getChildrenRecursively(child.getId());

            // 如果没有子节点，设置 children 为 null 表示这是叶子节点
            if (subChildren.isEmpty()) {
                child.setChildren(null); // 叶子节点
            } else {
                child.setChildren(subChildren); // 非叶子节点
            }

            // 设置 label 为 menuName
            child.setLabel(child.getMenuName());
        }

        return children;
    }

}