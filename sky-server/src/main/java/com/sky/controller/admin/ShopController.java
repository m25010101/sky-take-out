package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@Slf4j
@Api(tags = "后台-商铺管理")
@RequestMapping("/admin/shop")
public class ShopController {
    @Autowired
    private RedisTemplate redisTemplate;
    String shopStatus = "SHOP_STATUS";

    @PutMapping("/{status}")
    @ApiOperation(value = "修改商铺状态")
    public Result updateShopStatus(@PathVariable("status") Integer status) {
        log.info("修改商铺状态，status={}", status);
        redisTemplate.opsForValue().set(shopStatus, status);
        return Result.success();
    }

    @GetMapping("/status")
    @ApiOperation(value = "获取商铺状态")
    public Result<Integer> getShopStatus() {
        Integer status = (Integer) redisTemplate.opsForValue().get(shopStatus);
        log.info("status={}", status == 1 ? "营业中" : "打烊中");
        return Result.success(status);
    }

}
