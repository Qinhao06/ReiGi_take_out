package com.qh.reigi.service;

import com.github.pagehelper.PageInfo;
import com.qh.reigi.common.R;
import com.qh.reigi.dto.DishDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface DishService {


    R<String> addDish(HttpServletRequest request, DishDto dishDto);

    DishDto getDishById(Long id);


    R<PageInfo<?>> getDishList(Integer pageNum, Integer pageSize, String name);

    R<String> changeStatus(HttpServletRequest request, int[] idList, Integer Status);

    R<String> deleteDish(HttpServletRequest request, int[] idList);

    R<List<DishDto>> getDishListByCategoryId(HttpServletRequest request, Long categoryId, Integer status);

    R<String> editDish(HttpServletRequest request, DishDto dishdto);
}
