package com.qh.reigi.controller;


import com.github.pagehelper.PageInfo;
import com.qh.reigi.common.R;
import com.qh.reigi.dao.mapper.SetMealMapper;
import com.qh.reigi.dto.SetmealDto;
import com.qh.reigi.service.SetMealService;
import com.qh.reigi.service.PageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Slf4j
@RestController
@RequestMapping("/setmeal")
public class SetMealController {

    @Autowired
    PageService pageService;

    @Autowired
    SetMealMapper setMealMapper;

    @Autowired
    SetMealService setMealService;


    @GetMapping("/page")
    public R<PageInfo<?>> page(@RequestParam("page") Integer pageNum, @RequestParam("pageSize") Integer pageSize, String name){
        return  setMealService.getSetMealPage(pageNum, pageSize, name);
    }

    @PostMapping("add")
    public R<String> add(HttpServletRequest request, @RequestBody SetmealDto setmealDto){
        return setMealService.addSetMeal(request, setmealDto);
    }

    @PostMapping("/status/{Status}")
    public R<String> status(HttpServletRequest request,@RequestParam("ids") String ids,  @PathVariable(value = "Status") Integer Status){
        int[] idList = Arrays.stream(ids.split(",")).mapToInt(Integer::valueOf).toArray();
        return setMealService.changeStatus(request, idList, Status);
    }

    @DeleteMapping("/delete")
    public R<String> delete(HttpServletRequest request,@RequestParam("ids") String ids){
        int[] idList = Arrays.stream(ids.split(",")).mapToInt(Integer::valueOf).toArray();
        return setMealService.deleteSetMeal(request, idList);
    }


}
