package com.fqishappy.domain.vo;

import com.fqishappy.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author fqishappy
 * @date 2024/9/23 14:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminGetUserInfoVO {
    private List<Long> roleIds;
    private List<Role> roles;
    private UserInfoVO user;


}
