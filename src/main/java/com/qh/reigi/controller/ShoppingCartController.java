package com.qh.reigi.controller;


import com.qh.reigi.common.R;
import com.qh.reigi.entity.BeanCartID;
import com.qh.reigi.entity.ShoppingCart;
import com.qh.reigi.entity.User;
import com.qh.reigi.service.ShoppingCartService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;


    @GetMapping("/list")
    public R<List<ShoppingCart>> listAll(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        return shoppingCartService.listAll(user.getId());
    }

    @PostMapping("/add")
    public R<String> add(HttpServletRequest request, @RequestBody ShoppingCart shoppingCart){
        return shoppingCartService.addShoppingCart(request, shoppingCart);
    }

    @PostMapping("/sub")
    public R<String> sub(HttpServletRequest request, @RequestBody BeanCartID cartID){
        return shoppingCartService.subShoppingCart(request, cartID.getDishId(), cartID.getSetmealId());
    }



}
