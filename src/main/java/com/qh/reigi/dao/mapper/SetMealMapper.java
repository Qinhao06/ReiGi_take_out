package com.qh.reigi.dao.mapper;


import com.qh.reigi.dto.SetmealDto;
import com.qh.reigi.entity.Dish;
import com.qh.reigi.entity.Setmeal;
import com.qh.reigi.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SetMealMapper {


    void insertSetMeal(@Param("setMeal") Setmeal setMeal);


    void changeStatus(@Param("id") int id, @Param("status") int status);

    void deleteSetMeal(@Param("id") int id, @Param("isDeleted") int isDeleted);

    Long getSetMealId(@Param("setmeal") Setmeal setmeal);

    void insertSetMealDish(@Param("setmealdish") SetmealDish setmealDish);

     List<Setmeal> getSetMealListByCategoryId(Long categoryId);

     List<SetmealDto> getSetMealListByCategoryIdAndStatus(Long categoryId, Integer status);

    Setmeal getSetMealByName(@Param("name") String name);

    List<SetmealDish> getSetMealDish(@Param("id") Long id);
}
