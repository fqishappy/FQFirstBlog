package com.fqishappy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fqishappy
 * @date 2024/9/23 16:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminLinkVO {
    private String name;
    private String description;
    private String address;
    private String logo;
    private String status;
}
