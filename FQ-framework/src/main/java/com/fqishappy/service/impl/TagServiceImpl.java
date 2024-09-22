package com.fqishappy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqishappy.constants.SystemConstants;
import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.dto.TagListDto;
import com.fqishappy.domain.entity.Tag;
import com.fqishappy.domain.vo.PageVO;
import com.fqishappy.domain.vo.TagAddVO;
import com.fqishappy.domain.vo.TagVO;
import com.fqishappy.mapper.TagMapper;
import com.fqishappy.service.TagService;

import com.fqishappy.utils.BeanCopyUtils;
import com.fqishappy.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2024-09-19 01:10:21
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TagMapper tagMapper;


    /**
     * 分页查询标签
     * @param pageNum
     * @param pageSize
     * @param tagListDto
     * @return
     */
    @Override
    public ResponseResult<PageVO> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasText(tagListDto.getName()),Tag::getName, tagListDto.getName());
        wrapper.eq(StringUtils.hasText(tagListDto.getRemark()),Tag::getRemark, tagListDto.getRemark());

        Page<Tag> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, wrapper);

        PageVO pageVO = new PageVO(page.getRecords(), page.getTotal());
        return ResponseResult.okResult(pageVO);
    }

    /**
     * 添加标签
     * @param tagAddVO
     * @return
     */
    @Override
    public ResponseResult addTag(TagAddVO tagAddVO) {
        Tag tag = BeanCopyUtils.copyBean(tagAddVO, Tag.class);
        tag.setCreateBy(SecurityUtils.getUserId());
        tag.setCreateTime(new Date());
        tagMapper.insert(tag);
        return ResponseResult.okResult();
    }

    /**
     * 删除
     * @param tagId
     * @return
     */
    @Override
    public ResponseResult deleteTagById(Long tagId) {
        tagMapper.logicDeleteTag(tagId);
        return ResponseResult.okResult();
    }

    /**
     * 更新
     * @param tagId
     * @param tagAddVO
     * @return
     */
    @Override
    public ResponseResult updateTagById(Long tagId, TagAddVO tagAddVO) {
        Tag tag = getById(tagId);
        BeanUtils.copyProperties(tag, tagAddVO);
        tag.setUpdateBy(SecurityUtils.getUserId());
        tag.setUpdateTime(new Date());
        tagMapper.updateById(tag);
        return ResponseResult.okResult();
    }

    /**
     * 通过id查询
     * @param tagId
     * @return
     */
    @Override
    public ResponseResult getTagById(Long tagId) {
        return ResponseResult.okResult(tagMapper.selectById(tagId));
    }

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<TagVO> listAllTag() {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();

        wrapper.select(Tag::getId, Tag::getName);
        List<Tag> tags = list(wrapper);
        List<TagVO> tagVOS = BeanCopyUtils.copyBeanList(tags, TagVO.class);
        return tagVOS;
    }
}