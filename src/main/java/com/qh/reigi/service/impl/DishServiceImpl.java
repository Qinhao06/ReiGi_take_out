package com.qh.reigi.service.impl;

import com.github.pagehelper.PageInfo;
import com.qh.reigi.common.R;
import com.qh.reigi.dao.mapper.DishMapper;
import com.qh.reigi.dto.DishDto;
import com.qh.reigi.entity.DishFlavor;
import com.qh.reigi.entity.Employee;
import com.qh.reigi.service.PageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.qh.reigi.common.Constant.*;
import static java.util.concurrent.TimeUnit.MINUTES;

@Slf4j
@Service
public class DishServiceImpl implements com.qh.reigi.service.DishService {

    @Autowired
    CacheManager cacheManager;

    @Autowired
    DishMapper dishMapper;

    @Autowired
    PageService pageService;

    @CacheEvict(value = "dishCache", key = "'dish_' + #dishDto.categoryId + '_' + #dishDto.status")
    @Override
    public R<String> addDish(HttpServletRequest request, DishDto dishDto) {
        Employee employee = (Employee)request.getSession().getAttribute("employee");
        dishDto.setCreateTime(LocalDateTime.now());
        dishDto.setCreateUser(employee.getId());
        dishDto.setUpdateTime(LocalDateTime.now());
        dishDto.setUpdateUser(employee.getId());
        dishDto.setSort(INITIAL_DISH_SORT);
        dishDto.setIsDeleted(INITIAL_DELETE);
        dishMapper.insertDish(dishDto);
        Long dishId = dishDto.getId();
        if (dishId == null)return R.error("填加失败");
        dishDto.setId(dishId);
        dishDto.getFlavors().forEach(flavor -> {
            flavor.setCreateUser(employee.getId());
            flavor.setUpdateUser(employee.getId());
            flavor.setIsDeleted(INITIAL_DELETE);
            flavor.setCreateTime(LocalDateTime.now());
            flavor.setUpdateTime(LocalDateTime.now());
            flavor.setDishId(dishDto.getId());
            dishMapper.insertDishFavor(flavor);
        });
        return R.success("菜品填加成功");
    }

    @Override
    public DishDto getDishById(Long id) {
        return dishMapper.getDishById(id);
    }

    @Override
    public R<PageInfo<?>> getDishList(Integer pageNum, Integer pageSize, String name) {
        PageInfo<?> page = pageService.getPage2(pageNum, pageSize, "dish", "id", name);
        return R.success(page);

    }

    @Override
    public R<String> changeStatus(HttpServletRequest request, int[] idList, Integer Status) {
        for (int id : idList) {
            dishMapper.changeStatus(id, Status);
        }
        return R.success("修改菜品状态成功");
    }

    @CacheEvict(value = "dishCache", allEntries = true)
    @Override
    public R<String> deleteDish(HttpServletRequest request, int[] idList) {
        for (int id : idList) {
            dishMapper.deleteDish(id, DEFAULT_IS_DELETE);
        }
        return R.success("菜品删除成功");
    }

    @Cacheable(value = "dishCache", key = "'dish_' + #categoryId + '_' + #status")
    @Override
    public R<List<DishDto>> getDishListByCategoryId(HttpServletRequest request, Long categoryId, Integer status ){

        List<DishDto> dishes = status == null ?
                dishMapper.getDishListByCategoryId(categoryId) :
                dishMapper.getDishListByCategoryIdAndStatus(categoryId, status);
        dishes.forEach(dish -> {
            List<DishFlavor> flavors = dishMapper.getDishFlavor(dish.getId());
            dish.setFlavors(flavors);
        });
        return R.success(dishes);
    }

    @CacheEvict(value = "dishCache", key = "'dish_' + #dishDto.categoryId + '_' + #dishDto.status")
    @Override
    public R<String> editDish(HttpServletRequest request, DishDto dishDto) {
        dishDto.setUpdateTime(LocalDateTime.now());
        dishMapper.updateDish(dishDto);
        return R.success("修改成功");

    }
}
