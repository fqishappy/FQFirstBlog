package com.fqishappy.service;

import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.entity.User;

/**
 * @author fqishappy
 * @date 2024/9/13 17:31
 */
public interface BlogLoginService {
    /**
     * 登录功能实现
     * @param user
     * @return
     */
    ResponseResult login(User user);
}
