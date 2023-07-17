package com.qh.reigi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qh.reigi.dao.mapper.EmployeeMapper;
import com.qh.reigi.dao.mapper.PageMapper;
import com.qh.reigi.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.qh.reigi.common.Constant.DEFAULT_IS_DELETE;

@Service
public class PageServiceImpl implements PageService {

    @Autowired
    PageMapper pageMapper;

    @Override
    public <T> PageInfo<T> getPage(int pageNum, int pageSize, String tableName, String orderBy) {
        PageHelper.startPage(pageNum, pageSize);
        List<T> list = pageMapper.getAll(tableName, orderBy);
        PageInfo<T> pageInfo = new PageInfo<>(list);
        pageInfo.setList(list.subList((pageNum - 1) * pageSize, Math.min(pageNum * pageSize, (int) pageInfo.getTotal())));
        return pageInfo;
    }

    @Override
    public <T> PageInfo<T> getPage2(int pageNum, int pageSize, String tableName, String orderBy, String name) {
        List<T> list = pageMapper.getDish(tableName, orderBy, DEFAULT_IS_DELETE - 1 );
        if(name != null){
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, Object> map = (HashMap<String, Object>) list.get(i);
                System.out.println(map.get("name"));
                if(map.get("name").equals(name)){
                    pageNum = (i / pageSize) + 1;
                    break;
                }
            }
        }
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<T> pageInfo = new PageInfo<>(list);
        pageInfo.setList(list.subList((pageNum - 1) * pageSize, Math.min(pageNum * pageSize, (int) pageInfo.getTotal())));
        List<T> infoList = pageInfo.getList();
        for (Object setMeal : infoList) {
            HashMap<String, Object> map = (HashMap<String, Object>) setMeal;
            Long categoryId = (Long)map.get("category_id");
            map.put("categoryName", pageMapper.getCategoryName(categoryId));
        }
        return pageInfo;
    }



}
