package com.server.emcloud.dao;


import com.server.emcloud.domain.User;
import org.springframework.stereotype.Repository;

/**
 *  用户
 * @Author: lyx
 * @Date: 2022/6/22
 */

@Repository
public interface UserMapper {
    //用户登陆
    public int login(String user_phone, String user_passwd);

    //用户注册
    public int addUser(User user);
}
