package com.qh.reigi.service.impl;


import com.github.pagehelper.PageInfo;
import com.qh.reigi.common.R;
import com.qh.reigi.dao.mapper.OrderMapper;
import com.qh.reigi.dao.mapper.ShoppingCartMapper;
import com.qh.reigi.dto.OrdersDto;
import com.qh.reigi.entity.*;
import com.qh.reigi.service.AddressBookService;
import com.qh.reigi.service.OrderService;
import com.qh.reigi.service.PageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.qh.reigi.common.Constant.WAIT_TO_PAY;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    PageService pageService;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    AddressBookService addressBookService;

    @Autowired
    ShoppingCartMapper shoppingCartMapper;



    @Override
    public PageInfo<?> getOrderList(HttpServletRequest request, Integer pageNum, Integer pageSize) {
        User user = (User) request.getSession().getAttribute("user");
        List<OrdersDto> orderList = orderMapper.getOrderList(user.getId(), null, null);
        for (OrdersDto ordersDto : orderList) {
            ordersDto.setOrderDetails(orderMapper.getOrderDetail(ordersDto.getId()));
        }
        PageInfo<OrdersDto> page = new PageInfo<>(orderList.subList((pageNum - 1) * pageSize, Math.min(pageNum * pageSize, orderList.size() )));
        page.setPageSize(pageSize);
        page.setTotal(orderList.size());
        page.setPageNum(pageNum);
        page.setPages((int) Math.ceil((double) orderList.size() / (double) pageSize));
        return page;
    }


    @Override
    public R<String> submit(HttpServletRequest httpServletRequest, Orders orders) {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        List<ShoppingCart> shoppingCarts = shoppingCartMapper.listAll(user.getId());
        BigDecimal amount = BigDecimal.ZERO;
        for (ShoppingCart shoppingCart : shoppingCarts) {
            amount = amount.add(shoppingCart.getAmount());
        }
        // 设置orders
        orders.setAmount(amount);
        AddressBook addressBook = addressBookService.getAddressBookById(orders.getAddressBookId());
        orders.setConsignee(addressBook.getConsignee());
        orders.setPhone(addressBook.getPhone());
        orders.setUserId(user.getId());
        orders.setOrderTime(LocalDateTime.now());
        orders.setCheckoutTime(LocalDateTime.now());
        orders.setUserName(user.getName());
        orders.setAddress(addressBook.getDetail());
        orders.setStatus(WAIT_TO_PAY);
        orderMapper.addOrders(orders);
        //设置order_detail
        for (ShoppingCart shoppingCart : shoppingCarts) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setNumber(shoppingCart.getNumber());
            orderDetail.setAmount(shoppingCart.getAmount());
            orderDetail.setName(shoppingCart.getName());
            orderDetail.setImage(shoppingCart.getImage());
            orderDetail.setOrderId(orders.getId());
            orderDetail.setDishId(shoppingCart.getDishId());
            orderDetail.setSetmealId(shoppingCart.getSetmealId());
            orderDetail.setDishFlavor(shoppingCart.getDishFlavor());
            orderMapper.addOrderDetail(orderDetail);
        }


        return R.success("提交成功");
    }

    @Override
    public PageInfo<?> getAllOrderList(HttpServletRequest httpServletRequest, Integer pageNum, Integer pageSize, Long id, String beginTime, String endTime) {
        List<OrdersDto> orderList = orderMapper.getOrderList(null, beginTime, endTime);
        for (OrdersDto ordersDto : orderList) {
            ordersDto.setOrderDetails(orderMapper.getOrderDetail(ordersDto.getId()));
        }
        if(id != null){
            int index = 0;
            for (OrdersDto ordersDto : orderList) {
                if (Objects.equals(ordersDto.getId(), id)) {
                   pageNum = index / pageSize + 1;
                    break;
                }
                index++;
            }
        }
        PageInfo<OrdersDto> page = new PageInfo<>(orderList.subList((pageNum - 1) * pageSize, Math.min(pageNum * pageSize, orderList.size() )));
        page.setPageSize(pageSize);
        page.setTotal(orderList.size());
        page.setPageNum(pageNum);
        page.setPages((int) Math.ceil((double) orderList.size() / (double) pageSize));
        return page;
    }

    @Override
    public void update(BeanOrderStatus beanOrderStatus) {
        orderMapper.update(beanOrderStatus);
    }
}
