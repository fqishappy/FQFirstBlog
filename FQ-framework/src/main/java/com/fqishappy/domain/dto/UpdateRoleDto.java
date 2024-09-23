package com.fqishappy.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author fqishappy
 * @date 2024/9/23 12:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoleDto {
    private Long id;
    private String remark;
    private String roleKey;
    private String roleName;
    private Integer roleSort;
    private String status;
    private List<Long> menuIds;
}
