package com.fqishappy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqishappy.constants.SystemConstants;
import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.entity.Comment;
import com.fqishappy.domain.vo.CommentVO;
import com.fqishappy.domain.vo.PageVO;
import com.fqishappy.enums.AppHttpCodeEnum;
import com.fqishappy.handler.exception.SystemException;
import com.fqishappy.mapper.CommentMapper;
import com.fqishappy.service.CommentService;
import com.fqishappy.service.UserService;
import com.fqishappy.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2024-09-16 16:10:14
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {


    @Autowired
    private UserService userService;

    @Override
    public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        //查询对应文章的根评论
        //判断
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType),Comment::getArticleId, articleId);
        queryWrapper.eq(Comment::getRootId, -1);
        //评论类型
        queryWrapper.eq(Comment::getType, commentType);
        //分页
        Page<Comment> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        List<CommentVO> commentVOList = toCommentVOList(page.getRecords());

        //查询所有根评论对应的子评论集合，并且赋值给对应的属性
        for (CommentVO commentVO : commentVOList) {
            List<CommentVO> children = getChildren(commentVO.getId());
            commentVO.setChildren(children);
        }

        return ResponseResult.okResult(new PageVO(commentVOList, page.getTotal()));
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        if (!StringUtils.hasText(comment.getContent())) {
            throw new SystemException(AppHttpCodeEnum.CONTENT_NULL);
        }
        save(comment);
        return ResponseResult.okResult();
    }

    private List<CommentVO> getChildren(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId, id).orderByAsc(Comment::getCreateTime);
        List<Comment> comments = list(queryWrapper);
        List<CommentVO> commentVOList = toCommentVOList(comments);
        return commentVOList;
    }
    private List<CommentVO> toCommentVOList(List<Comment> list) {
        List<CommentVO> commentVOs = BeanCopyUtils.copyBeanList(list, CommentVO.class);
        //遍历vo集合
        for (CommentVO commentVO : commentVOs) {
            //通过createBy查询用户的昵称
            String nickName = userService.getById(commentVO.getCreateBy()).getNickName();
            commentVO.setUsername(nickName);
            //通过toCommentUserid查询用户昵称，如果不为-1，进行赋值
            Long toCommentUserId = commentVO.getToCommentUserId();
            if (toCommentUserId != -1){
                String nickNameToComment = userService.getById(toCommentUserId).getNickName();
                commentVO.setToCommentUserName(nickNameToComment);
            }
        }


        return commentVOs;
    }
}
