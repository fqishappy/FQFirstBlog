package com.fqishappy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.dto.CategoryDto;
import com.fqishappy.domain.dto.UpdateCategoryDto;
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
     * 前台获取某个分类下的文章列表
     * @return
     */
    ResponseResult getCategoryList();

    /**
     * 写博文查询所有分类
     * @return
     */
    List<CategoryVO> listAllCategory();

    /**
     * 后台分页模糊查询分类列表
     * @return
     */
    ResponseResult getCategoryListPage(Integer pageNum, Integer pageSize, String name, String status);

    /**
     * 后台新增分类
     * @param category
     * @return
     */
    ResponseResult addCategory(CategoryDto category);

    /**
     * 获取某个分类信息
     * @param id
     * @return
     */
    ResponseResult getCategory(Long id);

    /**
     * 更新分类信息
     * @param category
     * @return
     */
    ResponseResult updateCategory(UpdateCategoryDto category);

    /**
     * 逻辑删除分类
     * @param ids
     * @return
     */
    ResponseResult deleteCategory(List<Long> ids);
}
