package com.fqishappy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fqishappy
 * @date 2024/9/13 16:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkVO {
    private Long id;
    private String name;
    private String logo;
    private String description;
    private String address;
}
