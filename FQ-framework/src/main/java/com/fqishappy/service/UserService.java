package com.fqishappy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.entity.User;

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
     * 修改用户信息
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
}
