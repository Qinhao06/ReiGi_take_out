package com.qh.reigi.controller;


import com.github.pagehelper.PageInfo;
import com.qh.reigi.common.R;
import com.qh.reigi.entity.Category;
import com.qh.reigi.service.CategoryService;
import com.qh.reigi.service.PageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @Autowired
    PageService pageService;

    @GetMapping("/page")
    public R<PageInfo<Category>> page(@RequestParam("page") Integer pageNum, @RequestParam("pageSize") Integer pageSize){
        return R.success(pageService.getPage(pageNum, pageSize, "category", "sort"));
    }

    @DeleteMapping("/delete")
    public R<String> delete(@RequestParam("ids") long id){
        return categoryService.deleteCategory(id);
    }

    @PutMapping("/edit")
    public R<String> edit(HttpServletRequest request, @RequestBody Category category){
        return categoryService.editCategory(request, category);
    }

    @PostMapping("/add")
    public R<String> add(HttpServletRequest request, @RequestBody Category category){
        return categoryService.addCategory(request, category);
    }

    @GetMapping("/list")
    public R<List<Map<String, Object>>> list(@RequestParam("type") Integer type){
        return R.success(categoryService.listCategory(type));
    }

    @GetMapping("/list/all")
    public R<List<Category>> listAll(){
        return R.success(categoryService.listCategoryAll());
    }

}
