package com.fqishappy.controller;

import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.vo.UpdateLinkVO;
import com.fqishappy.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author fqishappy
 * @date 2024/9/23 16:20
 */
@RestController("/content/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    /**
     * 后台分页模糊查询友链
     * @param pageNum
     * @param pageSize
     * @param name
     * @param status
     * @return
     */
    @GetMapping("/list")
    public ResponseResult getLinkList(@RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize,
                                      @RequestParam(required = false) String name,
                                      @RequestParam(required = false) String status) {
        return linkService.getLinkList(pageNum, pageSize, name, status);
    }

    /**
     * 后台修改友链时回显友链信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult getLink(@PathVariable Long id) {
        return linkService.getLink(id);
    }

    /**
     * 更新友链信息
     * @param link
     * @return
     */
    @PutMapping
    public ResponseResult updateLink(@RequestBody UpdateLinkVO link) {
        return linkService.updateLink(link);
    }

    /**
     * 逻辑删除友链
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseResult deleteLink(@PathVariable Long id) {
        return linkService.deleteLink(id);
    }
}

