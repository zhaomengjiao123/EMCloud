package com.server.emcloud.vo;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/01/9:24
 * @Description:
 */
public class EquipmentErroVO {

    private int product_id;
    //产品名称
    private String product_name;

    private String equipment_number;

    private Integer company_id;

    private Integer salesman_id;

    private String salesman_name;


    private String sell_time;

    private Integer erro_id;
    private Integer equipment_id;
    private String erro_content;
    private String erro_time;

    //每个城市的城市名字
    private String name;
    //这所城市的所有设备的报警数量
    private int value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Integer getErro_id() {
        return erro_id;
    }

    public void setErro_id(Integer erro_id) {
        this.erro_id = erro_id;
    }

    public Integer getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(Integer equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getErro_content() {
        return erro_content;
    }

    public void setErro_content(String erro_content) {
        this.erro_content = erro_content;
    }

    public String getErro_time() {
        return erro_time;
    }

    public void setErro_time(String erro_time) {
        this.erro_time = erro_time;
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
}
