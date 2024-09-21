package com.fqishappy.service;

import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.entity.User;

/**
 * @author fqishappy
 * @date 2024/9/19 01:50
 */
public interface SystemLoginService {

    /**
     * 登录
     * @param user
     * @return
     */
    ResponseResult login(User user);

    /**
     * 登出
     * @return
     */
    ResponseResult logout();
}