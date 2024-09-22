package com.fqishappy.controller;

import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.dto.TagListDto;
import com.fqishappy.domain.vo.TagAddVO;
import com.fqishappy.domain.vo.TagVO;
import com.fqishappy.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fqishappy
 * @date 2024/9/19 01:13
 */
@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 查询标签列表
     *
     * @return
     */
    @GetMapping("/list")
    public ResponseResult list(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        return tagService.pageTagList(pageNum, pageSize, tagListDto);
    }

    /**
     * 添加标签
     *
     * @param tagAddVO
     * @return
     */
    @PostMapping
    public ResponseResult addTag(@RequestBody TagAddVO tagAddVO) {
        return tagService.addTag(tagAddVO);
    }

    /**
     * 根据标签id删除标签
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    public ResponseResult deleteTag(@RequestParam String ids) {
        if (!ids.contains(",")) {
            tagService.deleteTagById(Long.valueOf(ids));
        } else {
            String[] idArr = ids.split(",");
            for (String id : idArr) {
                tagService.deleteTagById(Long.valueOf(id));
            }
        }
        return ResponseResult.okResult();
    }


    /**
     * 根据标签id查询标签
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult getTagById(@PathVariable Long id) {
        return tagService.getTagById(id);
    }

    /**
     * 根据标签id修改标签
     *
     * @param tagAddVO
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseResult updateTagById(@PathVariable Long id, @RequestBody TagAddVO tagAddVO) {
        return tagService.updateTagById(id, tagAddVO);
    }

    /**
     * 查询所有标签
     * @return
     */
    @GetMapping("/listAllTag")
    public ResponseResult listAllTag() {
        List<TagVO> list = tagService.listAllTag();
        return ResponseResult.okResult(list);
    }
}
