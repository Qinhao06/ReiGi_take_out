package com.qh.reigi.dao.mapper;

import com.qh.reigi.common.R;
import com.qh.reigi.dto.DishDto;
import com.qh.reigi.entity.Dish;
import com.qh.reigi.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DishMapper {


    void insertDishFavor(@Param("dishFlavor") DishFlavor dishFlavor);

    void insertDish(@Param("dish") Dish dish);

    Dish getDishByName(@Param("name") String name);

    void changeStatus(@Param("id") int id, @Param("status") int status);

    void deleteDish(@Param("id") int id, @Param("isDeleted") int isDeleted);

    List<DishDto> getDishListByCategoryId(@Param("categoryId") Long categoryId);

    List<DishDto> getDishListByCategoryIdAndStatus(@Param("categoryId") Long categoryId, @Param("status") Integer status);

    List<DishFlavor> getDishFlavor(@Param("id") Long id);

    DishDto getDishById(@Param("id") Long id);
}
