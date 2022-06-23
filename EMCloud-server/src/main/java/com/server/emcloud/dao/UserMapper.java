package com.server.emcloud.dao;


import com.server.emcloud.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    //用户更新信息
    public int updateInfo(User user);

    //用户修改密码
    public int updatePasswd(User user);

    //管理员更改权限
    public int updateAuth(User user);

    //管理员删除用户，用户注销
    public int deleteUser(String user_phone);

    //超级管理员获取用户信息
    public List<User> getAllUser();

    //管理员获取用户信息
    public List<User> getUser(Integer user_company);

    //用户获取自己信息
    public User getUserOfPhone(String user_phone);

}
