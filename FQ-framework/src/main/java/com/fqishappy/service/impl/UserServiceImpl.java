package com.fqishappy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.entity.RoleMenu;
import com.fqishappy.domain.entity.User;
import com.fqishappy.domain.entity.UserRole;
import com.fqishappy.domain.vo.*;
import com.fqishappy.enums.AppHttpCodeEnum;
import com.fqishappy.handler.exception.SystemException;
import com.fqishappy.mapper.RoleMapper;
import com.fqishappy.mapper.UserMapper;
import com.fqishappy.mapper.UserRoleMapper;
import com.fqishappy.service.RoleMenuService;
import com.fqishappy.service.RoleService;
import com.fqishappy.service.UserRoleService;
import com.fqishappy.service.UserService;
import com.fqishappy.utils.BeanCopyUtils;
import com.fqishappy.utils.SecurityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2024-09-16 16:57:56
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserRoleMapper userRoleMapper ;
    /**
     * 返回用户的信息
     *
     * @return
     */
    @Override
    public ResponseResult userInfo() {
        Long userId = SecurityUtils.getUserId();
        User user = getById(userId);
        UserInfoVO userInfoVO = BeanCopyUtils.copyBean(user, UserInfoVO.class);
        return ResponseResult.okResult(userInfoVO);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {
        updateById(user);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult register(User user) {
        //非空判断
        if (!StringUtils.hasText(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getPassword())) {
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getEmail())) {
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getNickName())) {
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        //判断用户传给我们的用户名是否在数据库已经存在。userNameExist方法是下面定义的
        if (userNameExist(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        //判断用户传给我们的昵称是否在数据库已经存在。NickNameExist方法是下面定义的
        if (NickNameExist(user.getNickName())) {
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }
        //判断用户传给我们的邮箱是否在数据库已经存在。NickNameExist方法是下面定义的
        if (EmailExist(user.getEmail())) {
            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        }
        //判断用户传给我们的手机号码是否在数据库已经存在。PhonenumberExist方法是下面定义的
        if (PhonenumberExist(user.getPhonenumber())) {
            throw new SystemException(AppHttpCodeEnum.PHONENUMBER_EXIST);
        }
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        save(user);
        return ResponseResult.okResult();
    }

    /**
     * 分页模糊查询用户列表
     *
     * @param pageNum
     * @param pageSize
     * @param userName
     * @param phonenumber
     * @return
     */
    @Override
    public ResponseResult getAllUserList(Integer pageNum, Integer pageSize, String userName, String phonenumber) {
        Page<User> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(userName), User::getUserName, userName);
        queryWrapper.like(StringUtils.hasText(phonenumber), User::getPhonenumber, phonenumber);
        page(page, queryWrapper);
        List<User> records = page.getRecords();
        return ResponseResult.okResult(new PageVO(BeanCopyUtils.copyBeanList(records, ManagerUserVO.class), page.getTotal()));
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Override
    public ResponseResult addUser(AddUserVO user) {
        //非空判断
        if (!StringUtils.hasText(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getPassword())) {
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getEmail())) {
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getPhonenumber())) {
            throw new SystemException(AppHttpCodeEnum.PHONENUMBER_NOT_NULL);
        }
        //判断用户传给我们的用户名是否在数据库已经存在。userNameExist方法是下面定义的
        if (userNameExist(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        //判断用户传给我们的手机号是否在数据库已经存在。NickNameExist方法是下面定义的
        if (PhonenumberExist(user.getPhonenumber())) {
            throw new SystemException(AppHttpCodeEnum.PHONENUMBER_EXIST);
        }
        //判断用户传给我们的邮箱是否在数据库已经存在。NickNameExist方法是下面定义的
        if (EmailExist(user.getEmail())) {
            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        }
        //判断用户传给我们的手机号码是否在数据库已经存在。PhonenumberExist方法是下面定义的
        if (PhonenumberExist(user.getPhonenumber())) {
            throw new SystemException(AppHttpCodeEnum.PHONENUMBER_EXIST);
        }
        User userAdd = BeanCopyUtils.copyBean(user, User.class);
        String encode = passwordEncoder.encode(userAdd.getPassword());
        userAdd.setPassword(encode);
        userMapper.insert(userAdd);
        return ResponseResult.okResult();

    }

    /**
     * 逻辑删除用户
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteById(Long id) {
        userMapper.logicDeleteById(id);
        return ResponseResult.okResult();
    }

    /**
     * 修改用户时回显用户信息
     * @param id
     * @return
     */
    @Override
    public ResponseResult getUserById(Long id) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUserId, id);
        List<UserRole> list = userRoleService.list(queryWrapper);
        System.out.println("list"+ list);
        List<Long> collect = list.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        return ResponseResult.okResult(
                new AdminGetUserInfoVO(
                        collect,
                        roleService.list(),
                        BeanCopyUtils.copyBean(getById(id), UserInfoVO.class)));
    }

    /**
     * 后台修改用户信息
     * @param user
     * @return
     */
    @Override
    public ResponseResult updateUser(UpdateUserVO user) {
        //非空判断
        if (!StringUtils.hasText(user.getEmail())) {
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getNickName())) {
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        userMapper.updateById(BeanCopyUtils.copyBean(user, User.class));
        List<Long> roleIds = user.getRoleIds();
        Long id = user.getId();
        LambdaQueryWrapper<UserRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        for (Long roleId : roleIds) {
        lambdaQueryWrapper.eq(UserRole::getUserId, id).eq(UserRole::getRoleId, roleId);
            if (userRoleService.getOne(lambdaQueryWrapper) == null) {
                userRoleMapper.insert(new UserRole(id, roleId));
            }
        }
        return ResponseResult.okResult();

    }

    //'判断用户传给我们的用户名是否在数据库已经存在' 的方法
    private boolean userNameExist(String userName) {
        //要知道是否存在，首先就是根据条件去数据库查
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        //拿用户写的用户名跟数据库里面的用户名进行比较
        queryWrapper.eq(User::getUserName, userName);
        //如果大于0就说明从数据库查出来了，也就说明是已经存在数据库的
        boolean result = count(queryWrapper) > 0;
        //为true就说明已存在
        return result;
    }

    //'判断用户传给我们的昵称是否在数据库已经存在' 的方法
    private boolean NickNameExist(String nickName) {
        //要知道是否存在，首先就是根据条件去数据库查
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        //拿用户写的昵称跟数据库里面的昵称进行比较
        queryWrapper.eq(User::getNickName, nickName);
        //如果大于0就说明从数据库查出来了，也就说明是已经存在数据库的
        boolean result = count(queryWrapper) > 0;
        //为true就说明已存在
        return result;
    }

    //'判断用户传给我们的邮箱是否在数据库已经存在' 的方法
    private boolean EmailExist(String email) {
        //要知道是否存在，首先就是根据条件去数据库查
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        //拿用户写的昵称跟数据库里面的昵称进行比较
        queryWrapper.eq(User::getEmail, email);
        //如果大于0就说明从数据库查出来了，也就说明是已经存在数据库的
        boolean result = count(queryWrapper) > 0;
        //为true就说明已存在
        return result;
    }

    //'判断用户传给我们的手机号码是否在数据库已经存在' 的方法
    private boolean PhonenumberExist(String Phonenumber) {
        //要知道是否存在，首先就是根据条件去数据库查
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        //拿用户写的手机号码跟数据库里面的手机号码进行比较
        queryWrapper.eq(User::getPhonenumber, Phonenumber);
        //如果大于0就说明从数据库查出来了，也就说明是已经存在数据库的
        boolean result = count(queryWrapper) > 0;
        //为true就说明已存在
        return result;
    }

}
