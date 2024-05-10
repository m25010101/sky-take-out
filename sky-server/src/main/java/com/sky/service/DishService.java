package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DishService {
    public void saveWithFlavor(DishDTO dishDTO);

    PageResult QueryByPage(DishPageQueryDTO dishPageQueryDTO);

    void deleteBatch(List<Long> ids);

    DishDTO queryByIdWithFlavor(Long id);

    void updateWithFlavor(DishDTO dishDTO);
    List<DishVO> listWithFlavor(Dish dish);
}
