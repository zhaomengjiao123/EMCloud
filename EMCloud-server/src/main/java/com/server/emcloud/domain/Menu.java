package com.server.emcloud.domain;

import java.io.Serializable;
import java.util.List;

/**
 *  用户菜单
 * @Author: lyx
 * @Date: 2022/6/24
 */



public class Menu implements Serializable{


    private Integer menu_id;  //菜单编号id
    private String menu_name;  //菜单名称
    private String menu_link;  //菜单链接
    private Integer menu_parentid;  //菜单父节点id
    private Integer menu_visible;   //菜单可见性，1可见，0不可见
    private Integer menu_visible_user;   //菜单可见性，1可见，0不可见
    private Integer menu_visible_admin;   //菜单可见性，1可见，0不可见
    private Integer menu_visible_superadmin;   //菜单可见性，1可见，0不可见

    private List<Menu> children;   //菜单子节点集合


    public Integer getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Integer menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getMenu_link() {
        return menu_link;
    }

    public void setMenu_link(String menu_link) {
        this.menu_link = menu_link;
    }

    public Integer getMenu_parentid() {
        return menu_parentid;
    }

    public void setMenu_parentid(Integer menu_parentid) {
        this.menu_parentid = menu_parentid;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }


    public Integer getMenu_visible() {
        return menu_visible;
    }

    public void setMenu_visible(Integer menu_visible) {
        this.menu_visible = menu_visible;
    }

    public Integer getMenu_visible_user() {
        return menu_visible_user;
    }

    public void setMenu_visible_user(Integer menu_visible_user) {
        this.menu_visible_user = menu_visible_user;
    }

    public Integer getMenu_visible_admin() {
        return menu_visible_admin;
    }

    public void setMenu_visible_admin(Integer menu_visible_admin) {
        this.menu_visible_admin = menu_visible_admin;
    }

    public Integer getMenu_visible_superadmin() {
        return menu_visible_superadmin;
    }

    public void setMenu_visible_superadmin(Integer menu_visible_superadmin) {
        this.menu_visible_superadmin = menu_visible_superadmin;
    }



}
