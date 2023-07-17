package com.qh.reigi.dao.mapper;

import com.qh.reigi.common.R;
import com.qh.reigi.entity.Dish;
import com.qh.reigi.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DishMapper {


    void insertDishFavor(@Param("dishFlavor") DishFlavor dishFlavor);

    void insertDish(@Param("dish") Dish dish);

    Long getDishId(@Param("dish") Dish dish);

    void changeStatus(@Param("id") int id, @Param("status") int status);

    void deleteDish(@Param("id") int id, @Param("isDeleted") int isDeleted);

    <T> List<T> getDishListByCategoryId(@Param("categoryId") Long categoryId);
}
