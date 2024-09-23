package com.fqishappy.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fqishappy
 * @date 2024/9/23 01:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleStatusDto {
    private Long roleId;
    private String status;
}
