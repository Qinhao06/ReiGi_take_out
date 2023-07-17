package com.qh.reigi.service;


import com.github.pagehelper.PageInfo;
import com.qh.reigi.entity.Category;
import com.qh.reigi.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.lang.reflect.Method;
import java.util.List;

public interface PageService {

    <T> PageInfo<T> getPage(int pageNum, int pageSize, String tableName, String orderBy);

    <T> PageInfo<T> getPage2(int pageNum, int pageSize, String tableName, String orderBy, String name);

}
