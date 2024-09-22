package com.fqishappy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author fqishappy
 * @date 2024/9/22 13:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleAdminListVo {
    private Long categoryId;
    private String content;
    private Date CreateTime;
    private Integer delFlag;
    private Long id;
    private String isComment;
    private String isTop;
    private String status;
    private String summary;
    private String thumbnail;
    private String title;
    private Long viewCount;
}

