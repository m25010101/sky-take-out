package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE openid = #{openid}")
    public User getUserByOpenid(String openid);
    @Insert("insert into user(id, openid, name, phone, sex, id_number, avatar, create_time) " +
            "values (#{id}, #{openid}, #{name}, #{phone}, #{sex}, #{idNumber}, #{avatar}, #{createTime})")
    @Options(useGeneratedKeys = true,keyProperty="id")
    public void insertUser(User user);
}
