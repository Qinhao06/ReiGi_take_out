package com.qh.reigi;

import com.qh.reigi.dao.mapper.DishMapper;
import com.qh.reigi.entity.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

@SpringBootTest
public class TestBag {

    @Autowired
    DishMapper dishMapper;

    @Test
    public void test(){
        Dish dish = new Dish();
        dish.setName("哈哈");
        dish.setCreateUser(1L);
        Long dishId = dishMapper.getDishId(dish);
        System.out.println(dishId);
        assert dishId != null;
    }
}
