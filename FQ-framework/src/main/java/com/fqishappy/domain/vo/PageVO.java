package com.fqishappy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author fqishappy
 * @date 2024/9/13 12:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVO {
    private List rows;
    private Long total;
}
