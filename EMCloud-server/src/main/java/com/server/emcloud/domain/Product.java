package com.server.emcloud.domain;

/**
 * Created with IntelliJ IDEA.
 * 产品实体类
 * @Author: zmj
 * @Date: 2022/06/23/10:43
 * @Description:
 */
public class Product {

    //产品ID
    private int product_id;
    //产品名称
    private String product_name;
    //产品型号
    private String product_version;
    //产品发布日期
    private String product_release_date;
    //产品更新时间
    private String product_update_time;
    //产品图片地址
    private String product_pic_url;
    //产品编号
    private String product_number;
    //产品资料地址
    private String product_file_url;
    //产品备注
    private String product_comment;
    //产品类型ID
    private int product_type_id;

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

    public String getProduct_version() {
        return product_version;
    }

    public void setProduct_version(String product_version) {
        this.product_version = product_version;
    }

    public String getProduct_release_date() {
        return product_release_date;
    }

    public void setProduct_release_date(String product_release_date) {
        this.product_release_date = product_release_date;
    }

    public String getProduct_update_time() {
        return product_update_time;
    }

    public void setProduct_update_time(String product_update_time) {
        this.product_update_time = product_update_time;
    }

    public String getProduct_pic_url() {
        return product_pic_url;
    }

    public void setProduct_pic_url(String product_pic_url) {
        this.product_pic_url = product_pic_url;
    }

    public String getProduct_number() {
        return product_number;
    }

    public void setProduct_number(String product_number) {
        this.product_number = product_number;
    }

    public String getProduct_file_url() {
        return product_file_url;
    }

    public void setProduct_file_url(String product_file_url) {
        this.product_file_url = product_file_url;
    }

    public String getProduct_comment() {
        return product_comment;
    }

    public void setProduct_comment(String product_comment) {
        this.product_comment = product_comment;
    }

    public int getProduct_type_id() {
        return product_type_id;
    }

    public void setProduct_type_id(int product_type_id) {
        this.product_type_id = product_type_id;
    }
}
