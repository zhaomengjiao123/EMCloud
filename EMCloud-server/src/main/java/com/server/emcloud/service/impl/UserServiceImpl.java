package com.server.emcloud.service.impl;

import com.server.emcloud.dao.UserMapper;
import com.server.emcloud.domain.User;
import com.server.emcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public boolean login(String user_phone,String user_passwd){ return userMapper.login(user_phone, user_passwd)>0; }//用户登陆

    @Override
    public boolean addUser(User user){ return userMapper.addUser(user) >0; }//用户注册

    @Override
    public boolean updateInfo(User user) {
        return userMapper.updateInfo(user)>0;
    }

    @Override
    public boolean updateInfoAdmin(User user) {
        return userMapper.updateInfoAdmin(user)>0;
    }

    @Override
    public boolean updatePasswd(User user) {
        return userMapper.updatePasswd(user)>0;
    }

    @Override
    public boolean updateAuth(User user) {
        return userMapper.updateAuth(user)>0;
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public List<User> getUser(Integer user_company) {
        return userMapper.getUser(user_company);
    }

    @Override
    public boolean deleteUser(String user_phone) {
        return userMapper.deleteUser(user_phone)>0;
    }

    @Override
    public User getUserOfPhone(String user_phone) {
        return userMapper.getUserOfPhone(user_phone);
    }





}
