package com.qh.reigi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 套餐
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Setmeal implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;


    //分类id
    private Long categoryId;


    //套餐名称
    private String name;


    //套餐价格
    private BigDecimal price;


    //状态 0:停用 1:启用
    private Integer status;


    //编码
    private String code;


    //描述信息
    private String description;


    //图片
    private String image;


    private LocalDateTime createTime;


    private LocalDateTime updateTime;


    private Long createUser;



    private Long updateUser;


    //是否删除
    private Integer isDeleted;
}