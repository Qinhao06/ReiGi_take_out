package com.qh.reigi.service.impl;


import com.github.pagehelper.PageInfo;
import com.qh.reigi.common.R;
import com.qh.reigi.service.OrderService;
import com.qh.reigi.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    PageService pageService;

    @Override
    public R<PageInfo<?>> getOrderList(Integer pageNum, Integer pageSize, String name) {
        PageInfo<?> page = pageService.getPage(pageNum, pageSize, "orders", "id");
        return R.success(page);
    }
}
