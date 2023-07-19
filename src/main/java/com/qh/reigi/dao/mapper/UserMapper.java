package com.qh.reigi.dao.mapper;

import com.qh.reigi.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {


    User checkUserIsExist(@Param("phone") String phone);

    void register(@Param("user") User user);

    Long getUserId(@Param("phone") String phone);
}
