package com.qh.reigi.controller;

import com.github.pagehelper.PageInfo;
import com.qh.reigi.common.R;
import com.qh.reigi.entity.BeanOrderStatus;
import com.qh.reigi.entity.Orders;
import com.qh.reigi.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;


    @GetMapping("/userPage")
    public R<PageInfo<?>> userPage(HttpServletRequest httpServletRequest ,@RequestParam("page") Integer pageNum, @RequestParam("pageSize") Integer pageSize){
        return R.success(orderService.getOrderList(httpServletRequest,pageNum,pageSize));
    }

    @PostMapping("/submit")
    public R<String> submit(HttpServletRequest httpServletRequest,@RequestBody Orders orders){
        return orderService.submit(httpServletRequest, orders);
    }

    @GetMapping("/page")
    public R<PageInfo<?>> page(HttpServletRequest httpServletRequest,
                               @RequestParam("page") Integer pageNum, @RequestParam("pageSize") Integer pageSize , Long number, String beginTime, String endTime){
        return R.success(orderService.getAllOrderList(httpServletRequest,pageNum,pageSize, number,  beginTime, endTime));
    }

    @PutMapping("/edit")
    public R<String> update(@RequestBody BeanOrderStatus beanOrderStatus){
        orderService.update(beanOrderStatus);

        return R.success("修改成功bb");
    }
}
