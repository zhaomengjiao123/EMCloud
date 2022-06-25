package com.server.emcloud.dao;

import com.server.emcloud.domain.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 *  用户
 * @Author: lyx
 * @Date: 2022/6/24
 */

@Repository
public interface MenuMapper  {

    public List<Menu> getMenu();

    public List<Menu> getMenuAdmin();

    public List<Menu> getMenuSuperAdmin();

    public List<Menu> getMenuToAdmin();

    public List<Menu> getMenuToSuperAdmin();

    public List<Menu> getMenuAdminToSuperAdmin();

    public int updateMenuUserVis(Integer menu_id);  //用户对某功能可见

    public int updateMenuUserInvis(Integer menu_id);  //用户对某功能不可见

    public int updateMenuAdminVis(Integer menu_id);  //用户对某功能可见

    public int updateMenuAdminInvis(Integer menu_id);  //用户对某功能不可见

    public Menu getMenuOfId(String menu_id);
}
