package com.server.emcloud.service;

import com.server.emcloud.domain.Menu;

import java.util.List;

/**
 *  菜单
 * @Author: lyx
 * @Date: 2022/6/24
 */

public interface MenuService {

    public  Menu getMenuOfId(String menu_id);  //用户获取菜单

    public  List<Menu> getMenu();  //用户获取菜单

    public List<Menu> getMenuSuperAdmin();   //管理员获取菜单

    public List<Menu> getMenuAdmin();    //超级管理员获取菜单

    public  List<Menu> getMenuToAdmin();  //管理员获取用户菜单

    public List<Menu> getMenuToSuperAdmin();   //超级管理员获取用户菜单

    public List<Menu> getMenuAdminToSuperAdmin();   //超级管理员获取管理员菜单

    public Boolean updateMenuUserVis(Integer menu_id);  //用户对某功能可见

    public Boolean updateMenuUserInvis(Integer menu_id);  //用户对某功能不可见

    public Boolean updateMenuAdminVis(Integer menu_id);  //用户对某功能可见

    public Boolean updateMenuAdminInvis(Integer menu_id);  //用户对某功能不可见

}
