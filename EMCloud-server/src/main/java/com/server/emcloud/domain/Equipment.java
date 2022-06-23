package com.server.emcloud.domain;

public class Equipment {
    private Integer equipment_id;

    private String equipment_number;

    private Integer equipment_warning_count;

    private Integer equipment_erro_count;

    private Integer equipment_emergency_warning_count;

    private String equipment_comment;

    private Integer product_id;

    private Integer equipment_state;

    private Integer company_id;

    private Integer salesman_id;

    private String sell_time;

    public Integer getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(Integer equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getEquipment_number() {
        return equipment_number;
    }

    public void setEquipment_number(String equipment_number) {
        this.equipment_number = equipment_number == null ? null : equipment_number.trim();
    }

    public Integer getEquipment_warning_count() {
        return equipment_warning_count;
    }

    public void setEquipment_warning_count(Integer equipment_warning_count) {
        this.equipment_warning_count = equipment_warning_count;
    }

    public Integer getEquipment_erro_count() {
        return equipment_erro_count;
    }

    public void setEquipment_erro_count(Integer equipment_erro_count) {
        this.equipment_erro_count = equipment_erro_count;
    }

    public Integer getEquipment_emergency_warning_count() {
        return equipment_emergency_warning_count;
    }

    public void setEquipment_emergency_warning_count(Integer equipment_emergency_warning_count) {
        this.equipment_emergency_warning_count = equipment_emergency_warning_count;
    }

    public String getEquipment_comment() {
        return equipment_comment;
    }

    public void setEquipment_comment(String equipment_comment) {
        this.equipment_comment = equipment_comment == null ? null : equipment_comment.trim();
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getEquipment_state() {
        return equipment_state;
    }

    public void setEquipment_state(Integer equipment_state) {
        this.equipment_state = equipment_state;
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
        this.sell_time = sell_time == null ? null : sell_time.trim();
    }
}