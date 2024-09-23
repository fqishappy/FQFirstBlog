package com.fqishappy.controller;

import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.dto.UserStatusDto;
import com.fqishappy.domain.vo.AddUserVO;
import com.fqishappy.domain.vo.UpdateUserVO;
import com.fqishappy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author fqishappy
 * @date 2024/9/23 13:51
 */
@RestController
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 分页模糊查询用户列表
     * @param pageNum
     * @param pageSize
     * @param userName
     * @param phonenumber
     * @return
     */
    @GetMapping("/list")
    public ResponseResult getAllUserList(@RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize,
                                         @RequestParam(required = false) String userName,
                                         @RequestParam(required = false) String phonenumber) {
        return userService.getAllUserList(pageNum, pageSize, userName, phonenumber);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @PostMapping
    public ResponseResult addUser(@RequestBody AddUserVO user) {
        return userService.addUser(user);
    }

    /**
     * 逻辑删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseResult deleteUser(@PathVariable Long id) {
        return userService.deleteById(id);
    }

    /**
     * 修改用户时回显信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    /**
     * 后台修改用户信息
     * @param user
     * @return
     */
    @PutMapping
    public ResponseResult updateUser(@RequestBody UpdateUserVO user) {
        return userService.updateUser(user);
    }

    /**
     * 后台改变用户状态
     * @param userStatusDto
     * @return
     */
    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody UserStatusDto userStatusDto) {
        return userService.changeStatus(userStatusDto);
    }
}
