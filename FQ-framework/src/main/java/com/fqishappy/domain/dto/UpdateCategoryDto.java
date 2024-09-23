package com.fqishappy.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fqishappy
 * @date 2024/9/23 15:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryDto {
    private Long id;
    private String name;
    private String description;
    private String status;
}
