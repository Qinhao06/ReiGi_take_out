package com.qh.reigi.dto;


import com.qh.reigi.entity.OrderDetail;
import com.qh.reigi.entity.Orders;
import lombok.Data;
import java.util.List;

@Data
public class OrdersDto extends Orders {

    private String userName;

    private String phone;

    private String address;

    private String consignee;

    private List<OrderDetail> orderDetails;
	
}
