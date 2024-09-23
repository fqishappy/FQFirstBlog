package com.fqishappy.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.dto.CategoryDto;
import com.fqishappy.domain.dto.UpdateCategoryDto;
import com.fqishappy.domain.entity.Category;
import com.fqishappy.domain.vo.CategoryVO;
import com.fqishappy.domain.vo.ExcelCategoryVo;
import com.fqishappy.enums.AppHttpCodeEnum;
import com.fqishappy.service.CategoryService;
import com.fqishappy.utils.BeanCopyUtils;
import com.fqishappy.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fqishappy
 * @date 2024/9/21 15:54
 */
@RestController
@RequestMapping("/content/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 写博文查询所有分类
     * @return
     */
    @GetMapping("/listAllCategory")
    public ResponseResult listAllCategory() {
        List<CategoryVO> list = categoryService.listAllCategory();
        return ResponseResult.okResult(list);
    }

    /**
     * 导出分类excel
     * @param response
     */
    @PreAuthorize("@ps.hasPermission('content:category:export')")
    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        //设置下载文件的请求头
        try {
            WebUtils.setDownLoadHeader("分类.xlsx", response);
            List<Category> categories = categoryService.list();
            List<CategoryVO> categoryVOs = BeanCopyUtils.copyBeanList(categories, CategoryVO.class);
            //写入excel
            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class).autoCloseStream(Boolean.FALSE).sheet("分类导出").doWrite(categoryVOs);


        } catch (Exception e) {
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }

    }

    /**
     * 分页模糊查询分类列表
     * @return
     */
    @GetMapping("/list")
    public ResponseResult getCategoryListPage(@RequestParam Integer pageNum,
                                          @RequestParam Integer pageSize,
                                          @RequestParam(required = false) String name,
                                          @RequestParam(required = false) String status) {
        return categoryService.getCategoryListPage(pageNum, pageSize, name, status);
    }

    /**
     * 后台新增分类
     * @param category
     * @return
     */
    @PostMapping
    public ResponseResult addCategory(@RequestBody CategoryDto category) {
        return categoryService.addCategory(category);
    }

    /**
     * 获取某个分类信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult getCategory(@PathVariable Long id) {
        return categoryService.getCategory(id);
    }

    /**
     * 更新分类信息
     * @param category
     * @return
     */
    @PutMapping
    public ResponseResult updateCategory(@RequestBody UpdateCategoryDto category) {
        return categoryService.updateCategory(category);
    }

    /**
     * 逻辑删除分类
     * @param ids
     * @return
     */
    @DeleteMapping
    public ResponseResult deleteCategory(@RequestParam String ids) {
        String[] splits = ids.split(",");
        List<Long> collect = Arrays.stream(splits).map(Long::valueOf).collect(Collectors.toList());
        return categoryService.deleteCategory(collect);
    }
}
