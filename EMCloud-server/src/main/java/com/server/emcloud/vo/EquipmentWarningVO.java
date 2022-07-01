package com.server.emcloud.vo;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/01/0:39
 * @Description: 设备报警/预警/紧急警告
 */
public class EquipmentWarningVO {

    private int product_id;
    //产品名称
    private String product_name;

    private String equipment_number;

    private Integer company_id;

    private Integer salesman_id;

    private String salesman_name;


    private String sell_time;

    private Integer warning_id;
    private Integer equipment_id;
    private String  warning_content;
    private String  warning_time;

    public Integer getWarning_id() {
        return warning_id;
    }

    public void setWarning_id(Integer warning_id) {
        this.warning_id = warning_id;
    }

    public Integer getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(Integer equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getWarning_content() {
        return warning_content;
    }

    public void setWarning_content(String warning_content) {
        this.warning_content = warning_content;
    }

    public String getWarning_time() {
        return warning_time;
    }

    public void setWarning_time(String warning_time) {
        this.warning_time = warning_time;
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

    public String getSell_time() {
        return sell_time;
    }

    public void setSell_time(String sell_time) {
        this.sell_time = sell_time;
    }

    public String getSalesman_name() {
        return salesman_name;
    }

    public void setSalesman_name(String salesman_name) {
        this.salesman_name = salesman_name;
    }
}
