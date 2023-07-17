package com.qh.reigi.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PageMapper {
    <T> List<T> getAll(@Param("tableName") String tableName, @Param("orderBy") String orderBy);

    <T> List<T> getDish(@Param("tableName") String tableName, @Param("orderBy") String orderBy, @Param("isDeleted") int isDeleted);

    String getCategoryName(@Param("category") Long categoryId);
}
