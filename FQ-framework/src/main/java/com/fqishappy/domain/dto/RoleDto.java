package com.fqishappy.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author fqishappy
 * @date 2024/9/23 01:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto implements Serializable {

    private String roleName;
    private String roleKey;
    private Integer roleSort;
    private String status;
    private List<Long> menuIds;
    private String remark;
}
