package com.fqishappy.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fqishappy
 * @date 2024/9/23 16:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkDto {
    private String address;
    private String description;
    private Long id;
    private String logo;
    private String name;
    private String status;
}
