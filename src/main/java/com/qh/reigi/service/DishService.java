package com.qh.reigi.service;

import com.github.pagehelper.PageInfo;
import com.qh.reigi.common.R;
import com.qh.reigi.dto.DishDto;
import com.qh.reigi.entity.Dish;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface DishService {


    R<String> addDish(HttpServletRequest request, DishDto dishDto);


    R<PageInfo<?>> getDishList(Integer pageNum, Integer pageSize, String name);

    R<String> changeStatus(HttpServletRequest request, int[] idList, Integer Status);

    R<String> deleteDish(HttpServletRequest request, int[] idList);

    R<List<Dish>> getDishListByCategoryId(HttpServletRequest request, Long categoryId);
}
