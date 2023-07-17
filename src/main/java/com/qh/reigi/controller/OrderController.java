package com.qh.reigi.controller;

import com.github.pagehelper.PageInfo;
import com.qh.reigi.common.R;
import com.qh.reigi.service.DishService;
import com.qh.reigi.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/page")
    public R<PageInfo<?>> page(@RequestParam("page") Integer pageNum, @RequestParam("pageSize") Integer pageSize, String name){
        return  orderService.getOrderList(pageNum, pageSize, name);
    }
}
