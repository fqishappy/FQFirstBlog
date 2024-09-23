package com.fqishappy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author fqishappy
 * @date 2024/9/22 23:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleVO {
    private Date createTime;
    private String delFlag;
    private Long id;
    private String roleKey;
    private String roleName;
    private Integer roleSort;
    private String status;
    private Date updateTime;
}
