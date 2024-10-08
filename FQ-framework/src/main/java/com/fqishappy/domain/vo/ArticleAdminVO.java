package com.fqishappy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author fqishappy
 * @date 2024/9/22 14:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleAdminVO {
    /**
     * 所属分类id
     */
    private Long categoryId;

    /**
     * 文章内容
     */
    private String content;
    private Long createBy;
    private Date createTime;
    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    private Integer delFlag;
    private Long id;
    /**
     * 是否允许评论 1是，0否
     */
    private String isComment;
    /**
     * 是否置顶（0否，1是）
     */
    private String isTop;
    /**
     * 状态（0已发布，1草稿）
     */
    private String status;
    /**
     * 文章摘要
     */
    private String summary;

    private List<String> tags;
    /**
     * 缩略图
     */
    private String thumbnail;
    /**
     * 标题
     */
    private String title;

    private Long updateBy;

    private Date updateTime;
    /**
     * 访问量
     */
    private Long viewCount;

}
