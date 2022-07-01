package com.server.emcloud.controller;


import com.server.emcloud.domain.Menu;
import com.server.emcloud.domain.User;
import com.server.emcloud.service.MenuService;
import com.server.emcloud.service.UserService;
import com.server.emcloud.utils.Consts;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;
    private UserService userService;

    public MenuController(UserService userService) {
        this.userService = userService;
    }

    /**
     * @Description: GET方法
     *     用户登陆后进入页面
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/6/24
     */
    @RequestMapping(value = "/getmenu",method = RequestMethod.GET)
    public Object getMenu(HttpServletRequest request) throws JSONException {
        String user_phone = request.getParameter("user_phone").trim();
        System.out.println(user_phone);
        User user = userService.getUserOfPhone(user_phone);
        System.out.println("用户权限是："+user.getUser_auth());
        Integer user_auth=user.getUser_auth();

        if(user_auth==1){
            return getMenuSuperAdmin();
        }else if(user_auth==2){
            return getMenuAdmin();
        }else{
            return getMenu();
        }

    }

    /**
     * @Description: GET方法
     *     超级管理员查看用户菜单
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/6/24
     */
    @RequestMapping(value = "/getmenutosuperadmin",method = RequestMethod.GET)
    public Object getMenuToSuperAd() throws JSONException {
            return getMenuToAdmin();

    }

    /**
     * @Description: GET方法
     *     管理员查看用户菜单
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/6/24
     */
    @RequestMapping(value = "/getmenutoadmin",method = RequestMethod.GET)
    public Object getMenuToAd() throws JSONException {
        return getMenuToAdmin();
    }


    /**
     * @Description: GET方法
     *     超级管理员查看管理员菜单
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/6/24
     */
    @RequestMapping(value = "/getmenuadmintosuperadmin",method = RequestMethod.GET)
    public Object getMenuAdminToSuper() throws JSONException {
            return getMenuAdminToSuperAdmin();


    }

    /**
     * @Description: PUT方法
     *     超级管理员或者管理员修改用户权限,使其对该功能可见
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/6/24
     */
    @RequestMapping(value = "/updatemenuuservis",method = RequestMethod.PUT)
    public Object updateMenuUserVis(HttpServletRequest request) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        String menu_id = request.getParameter("menu_id").trim();
        System.out.println(menu_id);

        Menu menu = new Menu();
        menu = menuService.getMenuOfId(menu_id);
        if(menu.getMenu_visible_user()==1){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "修改失败，该功能用户已经可见");
            return jsonObject;
        }

        Boolean res = menuService.updateMenuUserVis(Integer.parseInt(menu_id));

        if (res){
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "修改成功");
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "修改失败");
            return jsonObject;
        }

    }

    /**
     * @Description: PUT方法
     *     超级管理员或者管理员修改用户权限,使其对该功能不可见
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/6/24
     */
    @RequestMapping(value = "/updatemenuuserinvis",method = RequestMethod.PUT)
    public Object updateMenuUserInvis(HttpServletRequest request) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        String menu_id = request.getParameter("menu_id").trim();
        System.out.println(menu_id);

        Menu menu = new Menu();
        menu = menuService.getMenuOfId(menu_id);
        if(menu.getMenu_visible_user()==0){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "修改失败，该功能用户已经不可见");
            return jsonObject;
        }

        Boolean res = menuService.updateMenuUserInvis(Integer.parseInt(menu_id));
        if (res){
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "修改成功");
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "修改失败");
            return jsonObject;
        }

    }

    /**
     * @Description: PUT方法
     *     超级管理员修改管理员权限，,使其对该功能可见
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/6/24
     */
    @RequestMapping(value = "/updatemenuadminvis",method = RequestMethod.PUT)
    public Object updateMenuAdminVis(HttpServletRequest request) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        String menu_id = request.getParameter("menu_id").trim();
        System.out.println(menu_id);

        Menu menu = new Menu();
        menu = menuService.getMenuOfId(menu_id);
        if(menu.getMenu_visible_admin()==1){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "修改失败，该功能用户已经可见");
            return jsonObject;
        }

        Boolean res = menuService.updateMenuAdminVis(Integer.parseInt(menu_id));
        if (res){
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "修改成功");
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "修改失败");
            return jsonObject;
        }

    }

    /**
     * @Description: PUT方法
     *     超级管理员修改管理员权限,使其对该功能不可见
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/6/24
     */
    @RequestMapping(value = "/updatemenuadmininvis",method = RequestMethod.PUT)
    public Object updateMenuAdmininvis(HttpServletRequest request) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        String menu_id = request.getParameter("menu_id").trim();
        System.out.println(menu_id);

        Menu menu = new Menu();
        menu = menuService.getMenuOfId(menu_id);
        if(menu.getMenu_visible_admin()==0){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "修改失败，该功能用户已经不可见");
            return jsonObject;
        }

        Boolean res = menuService.updateMenuAdminInvis(Integer.parseInt(menu_id));
        if (res){
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "修改成功");
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "修改失败");
            return jsonObject;
        }


    }

    /**
     * @Description: PUT方法
     *     超级管理员修改管理员权限,使其对该功能不可见
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/6/24
     */
    @RequestMapping(value = "/getmenuofid",method = RequestMethod.GET)
    //获得菜单信息
    public Menu getMenuOfId(HttpServletRequest request) {
        String menu_id = request.getParameter("menu_id").trim();
        return menuService.getMenuOfId(menu_id);
    }

    //超级管理员或者管理员查看用户菜单
    public List<Menu> getMenuToAdmin() {
        List<Menu> rslist=new ArrayList<>();
        List<Menu> menus = menuService.getMenuToAdmin();//查询当前菜单数据库中所有父子级记录
        //先将所有一级菜单添加至rslist
        System.out.println(menus);
        for(Menu menu:menus){
            if(menu.getMenu_parentid()==0){      //如果父级为0，则为顶级菜单
                rslist.add(menu);
            }
        }
        // 为父级菜单设置子菜单，getChild是递归调用的
        for (Menu Menu : rslist) {
            //传入父级菜单Id,以及所有查询结果
            Menu.setChildren(getChild(Menu.getMenu_id(), menus));
        }

        return rslist;
    }

    //超级管理员查看管理员菜单
    public List<Menu> getMenuAdminToSuperAdmin() {
        List<Menu> rslist=new ArrayList<>();
        List<Menu> menus = menuService.getMenuAdminToSuperAdmin();//查询当前菜单数据库中所有父子级记录
        //先将所有一级菜单添加至rslist
        System.out.println(menus);
        for(Menu menu:menus){
            if(menu.getMenu_parentid()==0){      //如果父级为0，则为顶级菜单
                rslist.add(menu);
            }
        }
        // 为父级菜单设置子菜单，getChild是递归调用的
        for (Menu Menu : rslist) {
            //传入父级菜单Id,以及所有查询结果
            Menu.setChildren(getChild(Menu.getMenu_id(), menus));
        }

        return rslist;
    }

    //超级管理员获得自己菜单
    public List<Menu> getMenuSuperAdmin() {
        List<Menu> rslist=new ArrayList<>();
        List<Menu> menus = menuService.getMenuSuperAdmin();//查询当前菜单数据库中所有父子级记录
        //先将所有一级菜单添加至rslist
        System.out.println(menus);
        for(Menu menu:menus){
            if(menu.getMenu_parentid()==0){      //如果父级为0，则为顶级菜单
                rslist.add(menu);
            }
        }
        // 为父级菜单设置子菜单，getChild是递归调用的
        for (Menu Menu : rslist) {
            //传入父级菜单Id,以及所有查询结果
            Menu.setChildren(getChild(Menu.getMenu_id(), menus));
        }

        return rslist;
    }

    //管理员获得自己菜单
    public List<Menu> getMenuAdmin() {
        List<Menu> rslist=new ArrayList<>();
        List<Menu> menus = menuService.getMenuAdmin();//查询当前菜单数据库中所有父子级记录
        //先将所有一级菜单添加至rslist
        for(Menu menu:menus){
            if(menu.getMenu_parentid()==0){      //如果父级为0，则为顶级菜单
                rslist.add(menu);
            }
        }
        // 为父级菜单设置子菜单，getChild是递归调用的
        for (Menu Menu : rslist) {
            //传入父级菜单Id,以及所有查询结果
            Menu.setChildren(getChild(Menu.getMenu_id(), menus));
        }

        return rslist;
    }

    //用户获得自己菜单
    //根据菜单ID获取其父子级数据
    public List<Menu> getMenu() {
        List<Menu> rslist=new ArrayList<>();
        List<Menu> menus = menuService.getMenu();//查询当前菜单数据库中所有父子级记录
        //先将所有一级菜单添加至rslist
        for(Menu menu:menus){
            if(menu.getMenu_parentid()==0){      //如果父级为0，则为顶级菜单
                rslist.add(menu);
            }
        }
        // 为父级菜单设置子菜单，getChild是递归调用的
        for (Menu Menu : rslist) {
            //传入父级菜单Id,以及所有查询结果
            Menu.setChildren(getChild(Menu.getMenu_id(), menus));
        }

        return rslist;
    }


    //传进父级ID,查找其子菜单
    public List<Menu> getChild(int id,List<Menu> menus){
        List<Menu> childList = new ArrayList<>();
        for (Menu menu : menus) {
            // 遍历所有属于父级节点的子节点
            if (StringUtils.isNotBlank(menu.getMenu_parentid().toString())) { //判断参数是否为空
                if (menu.getMenu_parentid().equals(id)) {
                    childList.add(menu);
                }
            }
        }
        //然后给所有子节点添加子节点
        for (Menu childmenu : childList) {
            // 遍历所有属于父级节点的子节点
            childmenu.setChildren(getChild(childmenu.getMenu_id(),menus));
        }
        // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

}
