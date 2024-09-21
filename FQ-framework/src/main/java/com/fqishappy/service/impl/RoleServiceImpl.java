package com.fqishappy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqishappy.domain.entity.Role;
import com.fqishappy.mapper.RoleMapper;
import com.fqishappy.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author makejava
 * @since 2024-09-19 14:58:57
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    /**
     * 查询用户的角色信息
     * @param id
     * @return
     */
    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        //判断是否是管理员，如果是，就返回集合中只需要有admin
        if(id == 1L){
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }

        //否则查询对应普通用户所具有的的角色信息
        return getBaseMapper().selectRoleKeyByOtherUserId(id);
    }
}