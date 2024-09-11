package com.fqishappy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fqishappy
 * @date 2024/9/11 15:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotArticleVO {

    private Long id;
    private String title;
    private String viewCount;
}
