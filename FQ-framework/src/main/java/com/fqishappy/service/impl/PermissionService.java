package com.fqishappy.service.impl;

import com.fqishappy.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fqishappy
 * @date 2024/9/22 13:40
 */

@Service("ps")
public class PermissionService {

    /**
     * 判断当前用户是否具有permission
     * @param permission
     * @return
     */
    public boolean hasPermission(String permission) {
        //超级管理员返回true
        if (SecurityUtils.isAdmin()){
            return true;
        }
        List<String> permissions = SecurityUtils.getLoginUser().getPermissions();
        return permissions.contains(permission);

    }

}
