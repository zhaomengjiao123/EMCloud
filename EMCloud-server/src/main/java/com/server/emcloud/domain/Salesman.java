package com.server.emcloud.domain;

public class Salesman {
    private Integer salesman_id;

    private String salesman_name;

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
        this.salesman_name = salesman_name == null ? null : salesman_name.trim();
    }
}