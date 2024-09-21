package com.fqishappy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.entity.Category;
import com.fqishappy.domain.vo.CategoryVO;

import java.util.List;

/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2024-09-13 09:31:32
 */
public interface CategoryService extends IService<Category> {

    /**
     * 获取某个分类下的文章列表
     * @return
     */
    ResponseResult getCategoryList();

    /**
     * 查询所有分类
     * @return
     */
    List<CategoryVO> listAllCategory();
}
