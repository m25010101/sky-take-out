package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sky.constant.MessageConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.exception.LoginFailedException;
import com.sky.mapper.UserMapper;
import com.sky.properties.WeChatProperties;
import com.sky.service.UserService;
import com.sky.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private WeChatProperties weChatProperties;
    @Autowired
    private UserMapper userMapper;
    private String url = "https://api.weixin.qq.com/sns/jscode2session";

    @Override
    public User wxLogin(UserLoginDTO userLoginDTO) {
        String openId = getOpenId(userLoginDTO.getCode());
        if (openId == null){
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }
        User user = userMapper.getUserByOpenid(openId);
        if (user == null){
            user = User.builder()
                    .openid(openId)
                    .createTime(LocalDateTime.now())
                    .build();
            userMapper.insertUser(user);
        }

        return user;
    }

    private String getOpenId(String code) {
        HashMap<String, String> map = new HashMap<>();
        map.put("grant_type", "authorization_code");
        map.put("appid", weChatProperties.getAppid());
        map.put("secret", weChatProperties.getSecret());
        map.put("js_code", code);
        String json = HttpClientUtil.doGet(url, map);
        JSONObject jsonObject = JSON.parseObject(json);
        String openId = jsonObject.getString("openid");
        return openId;

    }
}
