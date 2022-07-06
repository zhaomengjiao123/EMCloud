package com.server.emcloud.vo;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/01/10:08
 * @Description:
 */
public class EquipmentEmergencyVO {

    private int product_id;
    //产品名称
    private String product_name;

    private String equipment_number;

    private Integer company_id;

    private String company_name;

    private Integer salesman_id;

    private String salesman_name;


    private String sell_time;



    private Integer emergency_warning_id;
    private Integer equipment_id;
    private String emergency_warning_content;
    private String emergency_warning_time;

    public Integer getEmergency_warning_id() {
        return emergency_warning_id;
    }

    public void setEmergency_warning_id(Integer emergency_warning_id) {
        this.emergency_warning_id = emergency_warning_id;
    }

    public Integer getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(Integer equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getEmergency_warning_content() {
        return emergency_warning_content;
    }

    public void setEmergency_warning_content(String emergency_warning_content) {
        this.emergency_warning_content = emergency_warning_content;
    }

    public String getEmergency_warning_time() {
        return emergency_warning_time;
    }

    public void setEmergency_warning_time(String emergency_warning_time) {
        this.emergency_warning_time = emergency_warning_time;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getEquipment_number() {
        return equipment_number;
    }

    public void setEquipment_number(String equipment_number) {
        this.equipment_number = equipment_number;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public Integer getSalesman_id() {
        return salesman_id;
    }

    public void setSalesman_id(Integer salesman_id) {
        this.salesman_id = salesman_id;
    }

    public String getSalesman_name() {
        return salesman_name;
    }

    public void setSalesman_name(String salesman_name) {
        this.salesman_name = salesman_name;
    }

    public String getSell_time() {
        return sell_time;
    }

    public void setSell_time(String sell_time) {
        this.sell_time = sell_time;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
}
