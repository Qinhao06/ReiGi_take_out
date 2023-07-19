package com.qh.reigi.dao.mapper;

import com.github.pagehelper.PageInfo;
import com.qh.reigi.dto.OrdersDto;
import com.qh.reigi.entity.BeanOrderStatus;
import com.qh.reigi.entity.OrderDetail;
import com.qh.reigi.entity.Orders;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.core.annotation.Order;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<OrdersDto> getOrderList(@Param("id") Long id,@Param("beginTime") String beginTime,@Param("endTime") String endTime);

    void addOrders(@Param("orders") Orders orders);

    void addOrderDetail(@Param("ordersDetail") OrderDetail orderDetail);

    List<OrderDetail> getOrderDetail(@Param("id") Long id);

    void update(@Param("beanOrderStatus") BeanOrderStatus beanOrderStatus);
}
