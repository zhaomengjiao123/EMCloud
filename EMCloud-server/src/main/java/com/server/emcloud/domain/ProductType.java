package com.server.emcloud.domain;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/06/23/15:49
 * @Description:  产品类型
 */
public class ProductType {

    //产品类型ID
    private int product_type_id;
    //产品类型编号
    private String product_type_number;
    //产品类型名称
    private String product_type_name;
    //产品类型更新时间
    private String product_type_update_time;
    //产品类型备注
    private String product_type_comment;

    public int getProduct_type_id() {
        return product_type_id;
    }

    public void setProduct_type_id(int product_type_id) {
        this.product_type_id = product_type_id;
    }

    public String getProduct_type_number() {
        return product_type_number;
    }

    public void setProduct_type_number(String product_type_number) {
        this.product_type_number = product_type_number;
    }

    public String getProduct_type_name() {
        return product_type_name;
    }

    public void setProduct_type_name(String product_type_name) {
        this.product_type_name = product_type_name;
    }

    public String getProduct_type_update_time() {
        return product_type_update_time;
    }

    public void setProduct_type_update_time(String product_type_update_time) {
        this.product_type_update_time = product_type_update_time;
    }

    public String getProduct_type_comment() {
        return product_type_comment;
    }

    public void setProduct_type_comment(String product_type_comment) {
        this.product_type_comment = product_type_comment;
    }
}
