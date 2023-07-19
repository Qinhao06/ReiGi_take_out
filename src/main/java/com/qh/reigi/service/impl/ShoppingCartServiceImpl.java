package com.qh.reigi.service.impl;

import com.qh.reigi.common.R;
import com.qh.reigi.dao.mapper.DishMapper;
import com.qh.reigi.dao.mapper.SetMealMapper;
import com.qh.reigi.dao.mapper.ShoppingCartMapper;
import com.qh.reigi.entity.ShoppingCart;
import com.qh.reigi.entity.User;
import com.qh.reigi.service.ShoppingCartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ShoppingCartMapper shoppingCartMapper;

    @Autowired
    DishMapper dishMapper;

    @Autowired
    SetMealMapper setMealMapper;

    @Override
    public R<List<ShoppingCart>> listAll(Long userId) {
        List<ShoppingCart> shoppingCarts = shoppingCartMapper.listAll(userId);
        return R.success(shoppingCarts);
    }

    @Override
    public R<String> addShoppingCart(HttpServletRequest request, ShoppingCart shoppingCart) {
        Long dishId = shoppingCart.getDishId();
        Long setmealId = shoppingCart.getSetmealId();
        User user = (User) request.getSession().getAttribute("user");
        shoppingCart.setUserId(user.getId());
        shoppingCart.setCreateTime(LocalDateTime.now());

        ShoppingCart cart = shoppingCartMapper.selectByUserIdAndDishId(user.getId(), dishId, setmealId, shoppingCart.getDishFlavor());
        if (cart != null) {
            cart.setNumber(cart.getNumber() + 1);
            shoppingCartMapper.updateById(cart);
        }
        else {
            shoppingCartMapper.addShoppingCart(shoppingCart);
        }


        return R.success("添加cart成功");
    }

    @Override
    public R<String> subShoppingCart(HttpServletRequest request, Long dishId, Long setmealId) {
        List<ShoppingCart> shoppingCarts = shoppingCartMapper.listByDishId(dishId, setmealId);
        if (shoppingCarts != null){
            shoppingCarts.forEach(shoppingCart -> {
                Integer number = shoppingCart.getNumber();
                if(number == 1){
                    shoppingCartMapper.deleteById(shoppingCart.getId());
                }
                else{
                    shoppingCart.setNumber(number - 1);
                    shoppingCartMapper.updateById(shoppingCart);
                }
            });
        }
        return R.success("修改成功");
    }
}
