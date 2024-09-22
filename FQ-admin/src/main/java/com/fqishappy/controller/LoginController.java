package com.fqishappy.controller;

import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.entity.LoginUser;
import com.fqishappy.domain.entity.Menu;
import com.fqishappy.domain.entity.User;
import com.fqishappy.domain.vo.AdminUserInfoVO;
import com.fqishappy.domain.vo.RoutersVO;
import com.fqishappy.domain.vo.UserInfoVO;
import com.fqishappy.enums.AppHttpCodeEnum;
import com.fqishappy.handler.exception.SystemException;
import com.fqishappy.service.MenuService;
import com.fqishappy.service.RoleService;
import com.fqishappy.service.SystemLoginService;
import com.fqishappy.utils.BeanCopyUtils;
import com.fqishappy.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author fqishappy
 * @date 2024/9/19 01:10
 */
@RestController
public class LoginController {

    @Autowired
    private SystemLoginService systemLoginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    /**
     * 登录接口
     * @param user
     * @return
     */
    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return systemLoginService.login(user);
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/getInfo")
    public ResponseResult<AdminUserInfoVO> getInfo(){
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据用户id查询权限信息
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());
        //根据用户id查询角色信息
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());

        //获取用户信息
        User user = loginUser.getUser();
        UserInfoVO userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVO.class);

        //封装响应返回
        AdminUserInfoVO adminUserInfoVo = new AdminUserInfoVO(perms,roleKeyList,userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);
    }

    /**
     * 查询用户的路由信息
     * @return
     */
    @GetMapping("/getRouters")
    public ResponseResult<RoutersVO> getRouters(){
        //获取用户id
        Long userId = SecurityUtils.getUserId();

        //根据用户id来查询menu(权限菜单)。要求查询结果是tree的形式，也就是子父菜单树
        List<Menu> menus = menuService.selectRouterMenuTreeByUserId(userId);
        //封装响应返回
        return ResponseResult.okResult(new RoutersVO(menus));
    }

    /**
     * 登出接口
     * @return
     */
    @PostMapping("/user/logout")
    public ResponseResult logout() {
        return systemLoginService.logout();
    }

}