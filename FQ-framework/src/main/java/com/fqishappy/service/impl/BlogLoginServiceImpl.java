package com.fqishappy.service.impl;

import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.entity.LoginUser;
import com.fqishappy.domain.entity.User;
import com.fqishappy.domain.vo.BlogUserLoginVO;
import com.fqishappy.domain.vo.UserInfoVO;
import com.fqishappy.service.BlogLoginService;
import com.fqishappy.utils.BeanCopyUtils;
import com.fqishappy.utils.JwtUtil;
import com.fqishappy.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author fqishappy
 * @date 2024/9/13 17:37
 */
@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    /**
     * 登录功能实现
     * @param user
     * @return
     */
    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if(Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userid 生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息存入redis
        redisCache.setCacheObject("bloglogin:" + userId, loginUser);
        //把token和userinfo封装 返回
        //把user转换成userinfovo
        UserInfoVO userInfoVO = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVO.class);
        BlogUserLoginVO blogUserLoginVO = new BlogUserLoginVO(jwt, userInfoVO);
        return ResponseResult.okResult(blogUserLoginVO);
    }
}

