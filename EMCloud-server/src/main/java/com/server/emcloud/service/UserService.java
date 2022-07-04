package com.server.emcloud.service;

import com.server.emcloud.domain.User;

import java.util.List;

/**
 *  用户
 * @Author: lyx
 * @Date: 2022/6/22
 */
public interface UserService {

    public boolean login(String user_phone, String user_passwd);//用户注册

    public boolean addUser(User user);  //用户注册

    public boolean updateInfo(User user);//用户更新信息

    public boolean updateInfoAdmin(User user);//管理员更新用户信息

    public boolean updatePasswd(User user);//用户修改密码

    public boolean updateAuth(User user);//管理员更改权限

    public List<User> getAllUser();//超级管理员获取用户信息

    public List<User> getUser(Integer user_company);//管理员获取用户信息

    public User getUserOfPhone(String user_phone);//用户获取自己信息

    public boolean deleteUser(String user_phone);//管理员删除用户，用户注销

    public List<User> getUserOfPhone1(String user_phone);

    public boolean updateInfoSuperAdmin(User user);//超级管理员更新用户信息

    public List<User> getUserOfPhone2(String user_phone);

    public List<User> getUserOfPhone3(String user_phone, String company_id);
}
