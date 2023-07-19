package com.qh.reigi.service;

import com.github.pagehelper.PageInfo;
import com.qh.reigi.common.R;
import com.qh.reigi.entity.BeanOrderStatus;
import com.qh.reigi.entity.Orders;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;

public interface OrderService {
    PageInfo<?> getOrderList(HttpServletRequest request, Integer pageNum, Integer pageSize);

    R<String> submit(HttpServletRequest httpServletRequest, Orders orders);

    PageInfo<?> getAllOrderList(HttpServletRequest httpServletRequest, Integer pageNum, Integer pageSize, Long id, String beginTime, String endTime);

    void update(BeanOrderStatus beanOrderStatus);
}
