package com.qh.reigi.dao.mapper;


import com.qh.reigi.common.R;
import com.qh.reigi.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CategoryMapper {

    void deleteCategoryById(@Param("id") Long id);

    void updateCategory(@Param("category")Category category);

    void insertCategory(@Param("category")Category category);

    Integer sortIsExisted(@Param("sort") int sort);

    List<Map<String,Object>> getCategoryList(@Param("type") Integer type);



}
