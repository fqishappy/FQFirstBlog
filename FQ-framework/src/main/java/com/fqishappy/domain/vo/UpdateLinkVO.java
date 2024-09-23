package com.fqishappy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fqishappy
 * @date 2024/9/23 16:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLinkVO {
    private String address;
    private String description;
    private Long id;
    private String logo;
    private String name;
    private String status;
}
