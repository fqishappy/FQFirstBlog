package com.fqishappy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.dto.TagDto;
import com.fqishappy.domain.dto.TagListDto;
import com.fqishappy.domain.entity.Tag;
import com.fqishappy.domain.vo.PageVO;
import com.fqishappy.domain.vo.TagAddVO;
import com.fqishappy.domain.vo.TagVO;

import java.util.List;

/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2024-09-19 01:10:21
 */
public interface TagService extends IService<Tag> {

    /**
     * 分页查询tag
     * @param pageNum
     * @param pageSize
     * @param tagListDto
     * @return
     */
    ResponseResult<PageVO> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    /**
     * 增加标签
     * @return
     */
    ResponseResult addTag(TagAddVO tagAddVO);

    /**
     * 根据标签id删除标签
     * @param tagId
     * @return
     */
    ResponseResult deleteTagById(Long tagId);

    /**
     * 根据标签id修改标签
     * @param tagDto
     * @return
     */
    ResponseResult updateTagById(TagDto tagDto);

    /**
     * 根据标签id查询标签
     * @param tagId
     * @return
     */
    ResponseResult getTagById(Long tagId);

    /**
     * 查询所有标签
     * @return
     */
    List<TagVO> listAllTag();

}