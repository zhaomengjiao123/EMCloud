package com.server.emcloud.service;

import com.server.emcloud.domain.User;

/**
 *  用户
 * @Author: lyx
 * @Date: 2022/6/22
 */
public interface UserService {

    public boolean login(String user_phone, String user_passwd);//用户注册

    public boolean addUser(User user);  //用户注册
}
