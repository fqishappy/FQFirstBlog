package com.fqishappy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.entity.Category;

/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2024-09-13 09:31:32
 */
public interface CategoryService extends IService<Category> {

    /**
     * 获取分类列表
     * @return
     */
    ResponseResult getCategoryList();
}
