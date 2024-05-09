package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DishService {
    public void saveWithFlavor(DishDTO dishDTO);

    PageResult QueryByPage(DishPageQueryDTO dishPageQueryDTO);

    void deleteBatch(List<Long> ids);

    DishDTO queryByIdWithFlavor(Long id);

    void updateWithFlavor(DishDTO dishDTO);
}
