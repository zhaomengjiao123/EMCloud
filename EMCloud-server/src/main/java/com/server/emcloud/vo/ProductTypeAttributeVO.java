package com.server.emcloud.vo;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/06/24/16:32
 * @Description:
 */
public class ProductTypeAttributeVO {
    //前端额外需要的数据
    //每个属性对应的产品名称
    private String product_name;




    //产品属性ID
    private int product_attribute_id;
    //产品属性编号
    private String product_attribute_number;
    //产品属性名称
    private String product_attribute_name;
    //产品属性值
    private String product_attribute_value;
    //产品属性更新时间
    private String product_attribute_update_time;
    //产品维修值
    private String product_attribute_repair;
    //产品预警值
    private String product_attribute_warning;
    //产品报警值
    private String product_attribute_erro;
    //产品属性是否监控
    private int product_attribute_monitor;
    //产品属性所属的产品类型
    private int product_type_id;
    //产品属性备注
    private String product_attribute_comment;


    public int getProduct_attribute_id() {
        return product_attribute_id;
    }

    public void setProduct_attribute_id(int product_attribute_id) {
        this.product_attribute_id = product_attribute_id;
    }

    public String getProduct_attribute_number() {
        return product_attribute_number;
    }

    public void setProduct_attribute_number(String product_attribute_number) {
        this.product_attribute_number = product_attribute_number;
    }

    public String getProduct_attribute_name() {
        return product_attribute_name;
    }

    public void setProduct_attribute_name(String product_attribute_name) {
        this.product_attribute_name = product_attribute_name;
    }

    public String getProduct_attribute_value() {
        return product_attribute_value;
    }

    public void setProduct_attribute_value(String product_attribute_value) {
        this.product_attribute_value = product_attribute_value;
    }

    public String getProduct_attribute_update_time() {
        return product_attribute_update_time;
    }

    public void setProduct_attribute_update_time(String product_attribute_update_time) {
        this.product_attribute_update_time = product_attribute_update_time;
    }

    public String getProduct_attribute_repair() {
        return product_attribute_repair;
    }

    public void setProduct_attribute_repair(String product_attribute_repair) {
        this.product_attribute_repair = product_attribute_repair;
    }

    public String getProduct_attribute_warning() {
        return product_attribute_warning;
    }

    public void setProduct_attribute_warning(String product_attribute_warning) {
        this.product_attribute_warning = product_attribute_warning;
    }

    public String getProduct_attribute_erro() {
        return product_attribute_erro;
    }

    public void setProduct_attribute_erro(String product_attribute_erro) {
        this.product_attribute_erro = product_attribute_erro;
    }

    public int getProduct_attribute_monitor() {
        return product_attribute_monitor;
    }

    public void setProduct_attribute_monitor(int product_attribute_monitor) {
        this.product_attribute_monitor = product_attribute_monitor;
    }

    public int getProduct_type_id() {
        return product_type_id;
    }

    public void setProduct_type_id(int product_type_id) {
        this.product_type_id = product_type_id;
    }

    public String getProduct_attribute_comment() {
        return product_attribute_comment;
    }

    public void setProduct_attribute_comment(String product_attribute_comment) {
        this.product_attribute_comment = product_attribute_comment;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
}
