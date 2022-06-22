package com.server.emcloud.domain;
/**
* @Description: company实体类
* @Param:
* @return:
* @Author: zmj
* @Date: 2022/6/22
*/

public class Company {
    //机构名称
    private String company_name;
    //机构ID
    private int company_id;
    //机构类型
    private String company_type;
    //归属区域
    private String company_local;
    //公司备注
    private String company_comment;

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getCompany_type() {
        return company_type;
    }

    public void setCompany_type(String company_type) {
        this.company_type = company_type;
    }

    public String getCompany_local() {
        return company_local;
    }

    public void setCompany_local(String company_local) {
        this.company_local = company_local;
    }

    public String getCompany_comment() {
        return company_comment;
    }

    public void setCompany_comment(String company_comment) {
        this.company_comment = company_comment;
    }
}
