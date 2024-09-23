package com.fqishappy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fqishappy
 * @date 2024/9/23 03:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoleVO {
    private Long id;
    private String remark;
    private String roleKey;
    private String roleName;
    private Integer roleSort;
    private String status;
}
