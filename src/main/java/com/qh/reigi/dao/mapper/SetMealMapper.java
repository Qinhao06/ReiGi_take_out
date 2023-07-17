package com.qh.reigi.dao.mapper;


import com.qh.reigi.dto.SetmealDto;
import com.qh.reigi.entity.Setmeal;
import com.qh.reigi.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SetMealMapper {


    void insertSetMeal(@Param("setMeal") Setmeal setMeal);


    void changeStatus(@Param("id") int id, @Param("status") int status);

    void deleteSetMeal(@Param("id") int id, @Param("isDeleted") int isDeleted);

    Long getSetMealId(@Param("setmeal") Setmeal setmeal);

    void insertSetMealDish(@Param("setmealdish") SetmealDish setmealDish);
}
