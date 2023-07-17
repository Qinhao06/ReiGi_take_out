package com.qh.reigi.service;

import com.github.pagehelper.PageInfo;
import com.qh.reigi.common.R;
import com.qh.reigi.dto.DishDto;
import com.qh.reigi.dto.SetmealDto;
import com.qh.reigi.entity.Setmeal;
import jakarta.servlet.http.HttpServletRequest;

public interface SetMealService {


    R<String> addSetMeal(HttpServletRequest request, SetmealDto setmealDto);


    R<PageInfo<?>> getSetMealPage(Integer pageNum, Integer pageSize, String name);

    R<String> changeStatus(HttpServletRequest request, int[] idList, Integer Status);

    R<String> deleteSetMeal(HttpServletRequest request, int[] idList);
}
