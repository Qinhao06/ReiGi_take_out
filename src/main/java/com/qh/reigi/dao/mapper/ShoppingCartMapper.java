package com.qh.reigi.dao.mapper;


import com.qh.reigi.common.R;
import com.qh.reigi.entity.ShoppingCart;
import com.qh.reigi.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ShoppingCartMapper {
    List<ShoppingCart> listAll(@Param("userId") Long userId);

    void addShoppingCart(@Param("shoppingCart") ShoppingCart shoppingCart);

    ShoppingCart selectByUserIdAndDishId(@Param("id") Long id,@Param("dishId") Long dishId, @Param("setmealId") Long setMealId, @Param("dishFlavor") String dishFlavor);

    void updateById(@Param("cart") ShoppingCart cart);

    void subShoppingCart(@Param("dishId") Long dishId, @Param("userId") Long userId);

    List<ShoppingCart> listByDishId(@Param("dishId") Long dishId, @Param("setmealId") Long setmealId);

    void deleteById(@Param("id") Long id);
}
