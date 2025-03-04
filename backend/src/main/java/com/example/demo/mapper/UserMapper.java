package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user(username, password, role, email, phone, status) " +
            "VALUES(#{username}, #{password}, #{role}, #{email}, #{phone}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Select("SELECT * FROM user WHERE username = #{username}")
    User selectByUsername(String username);

    @Update("UPDATE user SET password=#{password}, email=#{email}, phone=#{phone}, status=#{status} " +
            "WHERE id=#{id}")
    void update(User user);

    @Delete("DELETE FROM user WHERE id=#{id}")
    void delete(Long id);
}