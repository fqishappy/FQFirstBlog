package com.fqishappy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.entity.User;
import com.fqishappy.domain.vo.AddUserVO;
import com.fqishappy.domain.vo.UpdateUserVO;

/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2024-09-16 16:57:56
 */
public interface UserService extends IService<User> {
    /**
     * 返回用户信息
     * @return
     */
    ResponseResult userInfo();

    /**
     * 前台修改用户信息
     * @param user
     * @return
     */
    ResponseResult updateUserInfo(User user);

    /**
     * 注册功能实现
     * @param user
     * @return
     */
    ResponseResult register(User user);

    /**
     * 分页模糊查询用户列表
     * @param pageNum
     * @param pageSize
     * @param userName
     * @param phonenumber
     * @return
     */
    ResponseResult getAllUserList(Integer pageNum, Integer pageSize, String userName, String phonenumber);

    /**
     * 添加用户
     * @param user
     * @return
     */
    ResponseResult addUser(AddUserVO user);

    /**
     * 逻辑删除用户
     * @param id
     * @return
     */
    ResponseResult deleteById(Long id);

    /**
     * 修改用户时回显用户信息
     * @param id
     * @return
     */
    ResponseResult getUserById(Long id);


    /**
     * 后台修改用户信息
     * @param user
     * @return
     */
    ResponseResult updateUser(UpdateUserVO user);
}
