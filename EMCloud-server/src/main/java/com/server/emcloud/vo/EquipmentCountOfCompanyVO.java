package com.server.emcloud.vo;

/**
 * author：王俊博
 * 查询每个公司的所有设备数
 */
public class EquipmentCountOfCompanyVO {
    Integer company_id;
    String company_name;
    Integer count;
    String company_local_city;

    public String getCompany_local_city() {
        return company_local_city;
    }

    public void setCompany_local_city(String company_local_city) {
        this.company_local_city = company_local_city;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


}
