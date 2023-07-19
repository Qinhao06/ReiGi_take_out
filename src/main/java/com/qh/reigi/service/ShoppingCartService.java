package com.qh.reigi.service;

import com.qh.reigi.common.R;
import com.qh.reigi.entity.ShoppingCart;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

public interface ShoppingCartService {
    R<List<ShoppingCart> >listAll(Long userId);

    R<String> addShoppingCart(HttpServletRequest request, ShoppingCart shoppingCart);

    R<String> subShoppingCart(HttpServletRequest request, Long dishId, Long setmealId);
}
