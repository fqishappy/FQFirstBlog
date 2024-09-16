package com.fqishappy.controller;

import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.entity.User;
import com.fqishappy.enums.AppHttpCodeEnum;
import com.fqishappy.handler.exception.SystemException;
import com.fqishappy.service.BlogLoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fqishappy
 * @date 2024/9/13 17:28
 */
@RestController

public class BlogLoginController {

    @Autowired
    private BlogLoginService blogLoginService;

    /**
     * 登录接口
     * @param user
     * @return
     */
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        if (StringUtils.hasText(user.getUserName())){
            //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }
}
