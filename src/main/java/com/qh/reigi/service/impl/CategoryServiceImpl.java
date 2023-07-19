package com.qh.reigi.service.impl;


import com.qh.reigi.common.R;
import com.qh.reigi.dao.mapper.CategoryMapper;
import com.qh.reigi.entity.Category;
import com.qh.reigi.entity.Employee;
import com.qh.reigi.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public R<String> deleteCategory(Long id) {
        try {
            categoryMapper.deleteCategoryById(id);
        }catch (Exception ex){
            return R.error("删除失败");
        }
        return R.success("删除成功");
    }

    @Override
    public R<String> editCategory(HttpServletRequest request, Category category) {

        Integer integer = categoryMapper.sortIsExisted(category.getSort());
        if(integer != null){
            return R.error("排序已被使用");
        }
        Employee employee = (Employee)request.getSession().getAttribute("employee");
        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(employee.getId());
        categoryMapper.updateCategory(category);
        return R.success("修改成功") ;
    }

    @Override
    public R<String> addCategory(HttpServletRequest request, Category category) {
        Integer integer = categoryMapper.sortIsExisted(category.getSort());
        if(integer != null){
            return R.error("排序已被使用");
        }
        Employee employee = (Employee)request.getSession().getAttribute("employee");
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateTime(LocalDateTime.now());
        category.setCreateUser(employee.getId());
        category.setUpdateUser(employee.getId());
        categoryMapper.insertCategory(category);
        return R.success("添加成功");
    }

    @Override
    public List<Map<String, Object>> listCategory(Integer type) {
        return categoryMapper.getCategoryList(type);
    }

    @Override
    public List<Category> listCategoryAll() {
        return categoryMapper.getCategoryListAll();
    }
}
