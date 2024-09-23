package com.fqishappy.controller;

import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.entity.Menu;
import com.fqishappy.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author fqishappy
 * @date 2024/9/22 17:13
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 查询所有菜单列表
     * @return
     */
    @GetMapping("/list")
    public ResponseResult getAllMenu(@RequestParam(required = false) Integer status, @RequestParam(required = false) String menuName) {
        return menuService.getAllMenu(status, menuName);
    }

    /**
     * 新增菜单
     * @param menu
     * @return
     */
    @PostMapping
    public ResponseResult addMenu(@RequestBody Menu menu) {
        menuService.save(menu);
        return ResponseResult.okResult();
    }

    /**
     * 查询菜单信息
     * @param menuId
     * @return
     */
    @GetMapping("/{menuId}")
    public ResponseResult getMenuById(@PathVariable("menuId") Long menuId) {
        return ResponseResult.okResult(menuService.getById(menuId));
    }


    /**
     * 更新菜单信息
     * @param menu
     * @return
     */
    @PutMapping
    public ResponseResult updateMenu(@RequestBody Menu menu) {
        menuService.updateById(menu);
        return ResponseResult.okResult();
    }

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    @DeleteMapping("/{menuId}")
    public ResponseResult deleteMenu(@PathVariable Long menuId){
        if (menuService.hasChild(menuId)){
            return ResponseResult.errorResult(500, "存在子菜单不允许删除");
        }
        return menuService.deleteMenu(menuId);
    }

    /**
     * 获取菜单树接口
     * @return
     */
    @GetMapping("/treeselect")
    public ResponseResult treeSelect(){
        return ResponseResult.okResult(menuService.treeSelect());
    }

    /**
     * 查询角色菜单列表树
     * @param id
     * @return
     */
    @GetMapping("/roleMenuTreeselect/{id}")
    public ResponseResult getRoleMenuTreeSelect(@PathVariable Long id) {
        return menuService.getRoleMenuTreeSelect(id);
    }
}
