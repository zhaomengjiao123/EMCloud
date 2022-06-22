package com.server.emcloud.service.impl;

import com.server.emcloud.dao.UserMapper;
import com.server.emcloud.domain.User;
import com.server.emcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  用户
 * @Author: lyx
 * @Date: 2022/6/22
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean login(String user_phone,String user_passwd){return userMapper.login(user_phone, user_passwd)>0;}//用户登陆

    @Override
    public boolean addUser(User user){return userMapper.addUser(user) >0;};  //用户注册



}
