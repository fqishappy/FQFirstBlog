package com.fqishappy.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.entity.Category;
import com.fqishappy.domain.vo.CategoryVO;
import com.fqishappy.domain.vo.ExcelCategoryVo;
import com.fqishappy.enums.AppHttpCodeEnum;
import com.fqishappy.service.CategoryService;
import com.fqishappy.utils.BeanCopyUtils;
import com.fqishappy.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

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
     * 查询所有分类
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
}
