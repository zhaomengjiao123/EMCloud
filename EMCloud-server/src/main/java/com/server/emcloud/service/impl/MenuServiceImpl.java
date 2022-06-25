package com.server.emcloud.service.impl;

import com.server.emcloud.dao.MenuMapper;
import com.server.emcloud.domain.Menu;
import com.server.emcloud.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  用户
 * @Author: lyx
 * @Date: 2022/6/24
 */

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Menu getMenuOfId(String menu_id) {
        return menuMapper.getMenuOfId(menu_id);
    }

    @Override
    public List<Menu> getMenu() {
        return menuMapper.getMenu();
    }

    @Override
    public List<Menu> getMenuSuperAdmin() {
        return menuMapper.getMenuSuperAdmin();
    }

    @Override
    public List<Menu> getMenuAdmin() {
        return menuMapper.getMenuAdmin();
    }

    @Override
    public List<Menu> getMenuToAdmin() {
        return menuMapper.getMenuToAdmin();
    }

    @Override
    public List<Menu> getMenuToSuperAdmin() {
        return menuMapper.getMenuToSuperAdmin();
    }

    @Override
    public List<Menu> getMenuAdminToSuperAdmin() {
        return menuMapper.getMenuAdminToSuperAdmin();
    }

    @Override
    public Boolean updateMenuUserVis(Integer menu_id) {
        return menuMapper.updateMenuUserVis(menu_id)>0;
    }

    @Override
    public Boolean updateMenuUserInvis(Integer menu_id) {
         return menuMapper.updateMenuUserInvis(menu_id)>0;
    }

    @Override
    public Boolean updateMenuAdminVis(Integer menu_id) {
         return menuMapper.updateMenuAdminVis(menu_id)>0;
    }

    @Override
    public Boolean updateMenuAdminInvis(Integer menu_id) {
        return menuMapper.updateMenuAdminInvis(menu_id)>0;
    }
}
