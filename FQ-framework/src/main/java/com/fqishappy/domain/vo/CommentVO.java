package com.fqishappy.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author fqishappy
 * @date 2024/9/16 16:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {
    @TableId
    private Long id;

    // 文章id
    private Long articleId;
    //子评论
    private List<CommentVO> children;
    // 根评论id
    private Long rootId;
    // 评论内容
    private String content;
    // 所回复的目标评论的userid
    private Long toCommentUserId;
    private String toCommentUserName;
    // 回复目标评论id
    private Long toCommentId;
    private Long createBy;
    private Date createTime;
    private String username;

}
