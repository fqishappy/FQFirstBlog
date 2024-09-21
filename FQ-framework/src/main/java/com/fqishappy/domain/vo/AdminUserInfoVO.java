package com.fqishappy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author fqishappy
 * @date 2024/9/19 14:56
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)

public class AdminUserInfoVO {

    private List<String> permissions;

    private List<String> roles;

    private UserInfoVO user;

}