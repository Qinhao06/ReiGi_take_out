package com.qh.reigi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qh.reigi.common.R;
import com.qh.reigi.dao.mapper.SetMealMapper;
import com.qh.reigi.dto.SetmealDto;
import com.qh.reigi.entity.Employee;
import com.qh.reigi.entity.SetmealDish;
import com.qh.reigi.service.PageService;
import com.qh.reigi.service.SetMealService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import static com.qh.reigi.common.Constant.*;

@Service
public class SetMealServiceImpl implements SetMealService {

    @Autowired
    PageService pageService;

    @Autowired
    SetMealMapper setMealMapper;


    @Override
    public R<String> addSetMeal(HttpServletRequest request, SetmealDto setmealDto) {
        Employee employee = (Employee) request.getSession().getAttribute("employee");
        setmealDto.setCreateUser(employee.getId());
        setmealDto.setUpdateUser(employee.getId());
        setmealDto.setCreateTime(LocalDateTime.now());
        setmealDto.setUpdateTime(LocalDateTime.now());
        setmealDto.setIsDeleted(INITIAL_DELETE);
        setMealMapper.insertSetMeal(setmealDto);
        Long setMealId = setMealMapper.getSetMealId(setmealDto);
        if (setMealId == null) return R.error("添加套餐失败");
        setmealDto.setId(setMealId);
        setmealDto.getSetmealDishes().forEach(setmealDish -> {
            setmealDish.setCreateUser(employee.getId());
            setmealDish.setUpdateUser(employee.getId());
            setmealDish.setCreateTime(LocalDateTime.now());
            setmealDish.setUpdateTime(LocalDateTime.now());
            setmealDish.setCopies(DEFAULT_COPIES);
            setmealDish.setSetmealId(setMealId);
            setmealDish.setIsDeleted(INITIAL_DELETE);
            setMealMapper.insertSetMealDish(setmealDish);
        });

        return R.success("套餐添加成功");
    }

    @Override
    public R<PageInfo<?>> getSetMealPage(Integer pageNum, Integer pageSize, String name) {
        System.out.println(name);
        PageInfo<?> page = pageService.getPage2(pageNum, pageSize, "setmeal", "id", name);
        return R.success(page);
    }

    @Override
    public R<String> changeStatus(HttpServletRequest request, int[] idList, Integer Status) {
        for (int id : idList) {
            setMealMapper.changeStatus(id, Status);
        }
        return R.success("修改菜品状态成功");
    }

    @Override
    public R<String> deleteSetMeal(HttpServletRequest request, int[] idList) {
        for (int id : idList) {
            setMealMapper.deleteSetMeal(id, DEFAULT_IS_DELETE);
        }
        return R.success("菜品删除成功");
    }
}
