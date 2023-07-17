package com.qh.reigi.dto;

import com.qh.reigi.entity.Setmeal;
import com.qh.reigi.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
