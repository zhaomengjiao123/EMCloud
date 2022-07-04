package com.server.emcloud.domain;

import java.io.Serializable;

/**
 *  部门类
 * @Author: lyx
 * @Date: 2022/7/2
 */

public class Depart implements Serializable {

    private String depart_name;   //部门名
    private String depart_id;       //部门id
    private String company_id;     //公司id

    public String getDepart_name() {
        return depart_name;
    }

    public void setDepart_name(String depart_name) {
        this.depart_name = depart_name;
    }

    public String getDepart_id() {
        return depart_id;
    }

    public void setDepart_id(String depart_id) {
        this.depart_id = depart_id;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }


}