package com.qh.reigi.service;

import com.github.pagehelper.PageInfo;
import com.qh.reigi.common.R;

public interface OrderService {
    R<PageInfo<?>> getOrderList(Integer pageNum, Integer pageSize, String name);
}
