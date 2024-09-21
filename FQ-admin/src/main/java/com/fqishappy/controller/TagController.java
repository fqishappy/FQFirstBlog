package com.fqishappy.controller;

import com.fqishappy.domain.ResponseResult;
import com.fqishappy.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     */
    @GetMapping("/list")
    public ResponseResult list(){
        //list是mybatisplus提供的方法
        return ResponseResult.okResult(tagService.list());
    }
}
