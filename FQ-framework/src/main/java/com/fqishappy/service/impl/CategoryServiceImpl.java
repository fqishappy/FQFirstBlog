package com.fqishappy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqishappy.constants.SystemConstants;
import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.dto.CategoryDto;
import com.fqishappy.domain.dto.UpdateCategoryDto;
import com.fqishappy.domain.entity.Article;
import com.fqishappy.domain.entity.Category;
import com.fqishappy.domain.vo.CategoryVO;
import com.fqishappy.domain.vo.PageVO;
import com.fqishappy.mapper.CategoryMapper;
import com.fqishappy.service.ArticleService;
import com.fqishappy.service.CategoryService;
import com.fqishappy.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2024-09-13 09:31:32
 */
@Service("categoryService")
@Transactional(rollbackFor = Exception.class)
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {


    @Autowired
    @Lazy
    private ArticleService articleService;

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 前台获取某个分类下的文章列表
     * @return
     */
    @Override
    public ResponseResult getCategoryList() {
        //查询文章表 状态为已发布
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> list = articleService.list(articleLambdaQueryWrapper);
        //获取文章分类id，并且需要去重
        Set<Long> categoryIds = list.stream()
                .map(Article::getCategoryId)
                .collect(Collectors.toSet());
        //查询分类表
        List<Category> categories = listByIds(categoryIds);
        categories = categories.stream().filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        //封装vo
        List<CategoryVO> categoryVOs = BeanCopyUtils.copyBeanList(categories, CategoryVO.class);

        return ResponseResult.okResult(categoryVOs);

    }

    /**
     * 获取所有分类
     * @return
     */
    @Override
    public List<CategoryVO> listAllCategory() {
        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryLambdaQueryWrapper.eq(Category::getStatus, SystemConstants.NORMAL);
        List<Category> list = list(categoryLambdaQueryWrapper);
        return BeanCopyUtils.copyBeanList(list, CategoryVO.class);
    }

    /**
     * 后台模糊分页查询分类列表
     * @param pageNum
     * @param pageSize
     * @param name
     * @param status
     * @return
     */
    @Override
    public ResponseResult getCategoryListPage(Integer pageNum, Integer pageSize, String name, String status) {
        Page<Category> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryLambdaQueryWrapper.eq(StringUtils.hasText(name), Category::getName, name);
        categoryLambdaQueryWrapper.eq(StringUtils.hasText(status), Category::getStatus, status);
        page(page, categoryLambdaQueryWrapper);
        List<Category> records = page.getRecords();
        List<CategoryVO> categoryVOS = BeanCopyUtils.copyBeanList(records, CategoryVO.class);
        return ResponseResult.okResult(new PageVO(categoryVOS, page.getTotal()));
    }

    /**
     * 后台新增分类
     * @param category
     * @return
     */
    @Override
    public ResponseResult addCategory(CategoryDto category) {
        categoryMapper.insert(BeanCopyUtils.copyBean(category, Category.class));
        return ResponseResult.okResult();
    }

    /**
     * 获取某个分类信息
     * @param id
     * @return
     */
    @Override
    public ResponseResult getCategory(Long id) {
        Category category = categoryMapper.selectById(id);
        CategoryVO categoryVO = BeanCopyUtils.copyBean(category, CategoryVO.class);
        return ResponseResult.okResult(categoryVO);
    }

    /**
     * 更新分类信息
     * @param category
     * @return
     */
    @Override
    public ResponseResult updateCategory(UpdateCategoryDto category) {
        Category updateCategory = BeanCopyUtils.copyBean(category, Category.class);
        updateById(updateCategory);
        return ResponseResult.okResult();
    }

    /**
     * 逻辑删除分类
     * @param ids
     * @return
     */
    @Override
    public ResponseResult deleteCategory(List<Long> ids) {
        for (Long id : ids) {
            categoryMapper.deleteCategory(id);
        }
        return ResponseResult.okResult();
    }


}
