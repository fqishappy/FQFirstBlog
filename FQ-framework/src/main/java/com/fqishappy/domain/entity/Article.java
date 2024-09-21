package com.fqishappy.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * 文章表(Article)实体类
 *
 * @author fqishappy
 * @since 2024-09-10 18:30:42
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("sg_article")
public class Article {

    @TableId
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 文章摘要
     */
    private String summary;
    /**
     * 所属分类id
     */
    private Long categoryId;

    /**
     * 所属分类id对应的名称
     */
    @TableField(exist = false)
    private String categoryName;
    /**
     * 缩略图
     */
    private String thumbnail;
    /**
     * 是否置顶（0否，1是）
     */
    private String isTop;
    /**
     * 状态（0已发布，1草稿）
     */
    private String status;
    /**
     * 访问量
     */
    private Long viewCount;
    /**
     * 是否允许评论 1是，0否
     */
    private String isComment;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;
    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    private Integer delFlag;

    public Article(Long id, Long viewCount) {
        this.id = id;
        this.viewCount = viewCount;
    }
}

