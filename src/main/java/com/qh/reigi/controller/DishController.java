package com.qh.reigi.controller;

import com.github.pagehelper.PageInfo;
import com.qh.reigi.common.R;
import com.qh.reigi.dao.mapper.DishMapper;
import com.qh.reigi.dto.DishDto;
import com.qh.reigi.entity.Dish;
import com.qh.reigi.service.DishService;
import com.qh.reigi.service.PageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    PageService pageService;

    @Autowired
    DishMapper dishMapper;

    @Autowired
    DishService dishService;


    @GetMapping("/page")
    public R<PageInfo<?>> page(@RequestParam("page") Integer pageNum, @RequestParam("pageSize") Integer pageSize, String name){
       return  dishService.getDishList(pageNum, pageSize, name);
    }

    @PostMapping("add")
    public R<String> add(HttpServletRequest request, @RequestBody DishDto dishdto){
        return dishService.addDish(request, dishdto);
    }

    @PostMapping("/status/{Status}")
    public R<String> Status(HttpServletRequest request,@RequestParam("ids") String ids,  @PathVariable(value = "Status") Integer Status){
        int[] idList = Arrays.stream(ids.split(",")).mapToInt(Integer::valueOf).toArray();
        return dishService.changeStatus(request, idList, Status);
    }

    @DeleteMapping("/delete")
    public R<String> delete(HttpServletRequest request,@RequestParam("ids") String ids){
        int[] idList = Arrays.stream(ids.split(",")).mapToInt(Integer::valueOf).toArray();
        return dishService.deleteDish(request, idList);
    }

    @GetMapping("/list")
    public R<List<Dish>> list(HttpServletRequest request, @RequestParam("categoryId") Long categoryId){
        return dishService.getDishListByCategoryId(request, categoryId);
    }
}
