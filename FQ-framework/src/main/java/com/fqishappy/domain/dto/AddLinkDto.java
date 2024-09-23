package com.fqishappy.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author fqishappy
 * @date 2024/9/23 18:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddLinkDto implements Serializable {
    private String name;
    private String logo;
    private String description;
    private String address;
    private String status;
}
