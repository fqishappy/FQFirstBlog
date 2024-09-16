package com.fqishappy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqishappy.domain.entity.User;
import com.fqishappy.mapper.UserMapper;
import com.fqishappy.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2024-09-16 16:57:56
 */
 @Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    
}
