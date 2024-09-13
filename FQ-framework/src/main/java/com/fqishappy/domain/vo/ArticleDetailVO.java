package com.fqishappy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author fqishappy
 * @date 2024/9/13 15:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailVO {
    private Long id;
    private String title;
    private String summary;
    private Long categoryId;
    private String categoryName;
    private String thumbnail;
    private String content;
    private Long viewCount;
    private Date CreateTime;
}
