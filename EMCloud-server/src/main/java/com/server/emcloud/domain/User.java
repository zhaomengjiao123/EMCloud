package com.server.emcloud.domain;


import java.io.Serializable;

/**
 *  用户类
 * @Author: lyx
 * @Date: 2022/6/22
 */

public class User implements Serializable {

    private String user_name;   //用户名
    private String user_phone;       //手机（登陆名，账号）
    private String user_tele;       //固定电话
    private String user_company;       //归属公司
    private String user_depart;       //归属部门
    private Integer company_id;       //归属公司id
    private Integer depart_id;       //归属部门id
    private String user_passwd;       //用户密码
    private Integer user_auth;       //用户权限,1为超级管理员，2为企业管理员，3为超级管理员

    public String getUser_company() {
        return user_company;
    }

    public void setUser_company(String user_company) {
        this.user_company = user_company;
    }

    public String getUser_depart() {
        return user_depart;
    }

    public void setUser_depart(String user_depart) {
        this.user_depart = user_depart;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public Integer getDepart_id() {
        return depart_id;
    }

    public void setDepart_id(Integer depart_id) {
        this.depart_id = depart_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_tele() {
        return user_tele;
    }

    public void setUser_tele(String user_tele) {
        this.user_tele = user_tele;
    }

    public String getUser_passwd() {
        return user_passwd;
    }

    public void setUser_passwd(String user_passwd) {
        this.user_passwd = user_passwd;
    }

    public Integer getUser_auth() {
        return user_auth;
    }

    public void setUser_auth(Integer user_auth) {
        this.user_auth = user_auth;
    }




}
