package com.server.emcloud.controller;

import com.server.emcloud.domain.User;
import com.server.emcloud.service.UserService;
import com.server.emcloud.utils.Consts;
import com.server.emcloud.utils.MD5;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @Description: POST方法
     *     用户登陆
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/6/22
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest request) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        boolean flag=false;
        String user_phone = request.getParameter("user_phone").trim();
        String user_passwd = request.getParameter("user_passwd" ).trim();
        if(user_phone!=null&&user_passwd!=null) {//判断登陆数据是否为空
            MD5 md = new MD5();
            user_passwd = md.start(user_passwd);//对密码进行md5加密，与数据库中的加密数据进行比较
            flag = userService.login(user_phone, user_passwd);//是否登陆成功

        }
        User user=userService.getUserOfPhone(user_phone);
        String user_name=user.getUser_name();
        System.out.println(user.getUser_auth());
        Integer user_auth=user.getUser_auth();
        if(flag){
            System.out.println("登陆成功");
            //HttpSession session = request.getSession();
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"登陆成功");
            jsonObject.put(Consts.NAME,user_name);
            jsonObject.put(Consts.PHONE,user_phone);
            jsonObject.put(Consts.AUTH,user_auth);


            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"用户名或密码错误");
        return jsonObject;
    }

    /**
     * @Description: POST方法
     *     用户注册
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/6/22
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addUser(HttpServletRequest req) throws JSONException {

        JSONObject jsonObject = new JSONObject();
        String user_name = req.getParameter("user_name").trim();
        System.out.println(user_name);
        String user_phone = req.getParameter("user_phone").trim();
        String user_tele = req.getParameter("user_tele").trim();
        String user_company = req.getParameter("user_company").trim();
        String user_depart = req.getParameter("user_depart").trim();
        String user_passwd = req.getParameter("user_passwd").trim();
       // String user_auth = req.getParameter("user_auth").trim();

        User user1=new User();
        user1=userService.getUserOfPhone(user_phone);
        if(user1!=null&&user1.getUser_phone().equals(user_phone)){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "该用户已注册");
            return jsonObject;
        }

        boolean res=false;

        if (user_passwd!=null) {//密码不能为空，为空md5后不为空，在usermapper里查不出来
            MD5 md = new MD5();
            user_passwd = md.start(user_passwd);

            User user = new User();
            user.setUser_name(user_name);
            user.setUser_phone(user_phone);
            user.setUser_tele(user_tele);
            user.setUser_company(Integer.parseInt(user_company));
            user.setUser_depart(Integer.parseInt(user_depart));
            user.setUser_passwd(user_passwd);
            user.setUser_auth(3);//用户默认权限为3（普通用户，超级管理员可以修改权限）

            res = userService.addUser(user);
        }
        if (res){
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "添加成功");
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "添加失败");
            return jsonObject;
        }
    }

    /**
     * @Description: POST方法
     *     管理员添加用户
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/6/23
     */
    @RequestMapping(value = "admin/add", method = RequestMethod.POST)
    public Object addUserAdmin(HttpServletRequest req) throws JSONException {

        JSONObject jsonObject = new JSONObject();
        String user_name = req.getParameter("user_name").trim();
        String user_phone = req.getParameter("user_phone").trim();
        String user_tele = req.getParameter("user_tele").trim();
        String user_company = req.getParameter("user_company").trim();
        String user_depart = req.getParameter("user_depart").trim();
        String user_passwd = "123456";//默认密码123456
        //String user_auth = req.getParameter("user_auth").trim();

        User user1=new User();
        user1=userService.getUserOfPhone(user_phone);
        if(user1!=null&&user1.getUser_phone().equals(user_phone)){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "该用户已存在");
            return jsonObject;
        }

        MD5 md = new MD5();
        user_passwd=md.start(user_passwd);

        User user = new User();
        user.setUser_name(user_name);
        user.setUser_phone(user_phone);
        user.setUser_tele(user_tele);
        user.setUser_company(Integer.parseInt(user_company));
        user.setUser_depart(Integer.parseInt(user_depart));
        user.setUser_passwd(user_passwd);
        user.setUser_auth(3);//用户默认权限为3（管理员添加普通用户）

        boolean res = userService.addUser(user);
        if (res){
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "添加成功");
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "添加失败");
            return jsonObject;
        }
    }

    /**
     * @Description: POST方法
     *     超级管理员添加管理员
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/6/22
     */
    @RequestMapping(value = "superadmin/add", method = RequestMethod.POST)
    public Object addUserSuperAdmin(HttpServletRequest req) throws JSONException {

        JSONObject jsonObject = new JSONObject();
        String user_name = req.getParameter("user_name").trim();
        String user_phone = req.getParameter("user_phone").trim();
        String user_tele = req.getParameter("user_tele").trim();
        String user_company = req.getParameter("user_company").trim();
        String user_depart = req.getParameter("user_depart").trim();
        String user_passwd = "123456";//默认密码123456
        //String user_auth = req.getParameter("user_auth").trim();

        User user1=new User();
        user1=userService.getUserOfPhone(user_phone);
        if(user1!=null&&user1.getUser_phone().equals(user_phone)){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "该用户已存在");
            return jsonObject;
        }

        MD5 md = new MD5();
        user_passwd=md.start(user_passwd);

        User user = new User();
        user.setUser_name(user_name);
        user.setUser_phone(user_phone);
        user.setUser_tele(user_tele);
        user.setUser_company(Integer.parseInt(user_company));
        user.setUser_depart(Integer.parseInt(user_depart));
        user.setUser_passwd(user_passwd);
        user.setUser_auth(2);//管理员默认权限为2（超级管理员添加管理员）

        boolean res = userService.addUser(user);
        if (res){
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "添加成功");
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "添加失败");
            return jsonObject;
        }
    }



    /**
     * @Description: POST方法
     *     用户修改信息
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/6/23
     */

    @RequestMapping(value = "updateinfo", method = RequestMethod.PUT)
    public Object updateInfo(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String user_name = req.getParameter("user_name").trim();
        String user_phone = req.getParameter("user_phone").trim();
        String user_tele = req.getParameter("user_tele").trim();

        User user = new User();
        user.setUser_name(user_name);
        user.setUser_phone(user_phone);
        user.setUser_tele(user_tele);


        boolean res = userService.updateInfo(user);
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
     * @Description: POST方法
     *     管理员修改信息
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/6/23
     */
    @RequestMapping(value = "/admin/updateinfo", method = RequestMethod.PUT)
    public Object updateInfoAdmin(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String user_name = req.getParameter("user_name").trim();
        String user_phone = req.getParameter("user_phone").trim();
        String user_tele = req.getParameter("user_tele").trim();
        String user_company = req.getParameter("user_company").trim();
        String user_depart = req.getParameter("user_depart").trim();

        User user = new User();
        user.setUser_name(user_name);
        user.setUser_phone(user_phone);
        user.setUser_tele(user_tele);
        user.setUser_company(Integer.parseInt(user_company));
        user.setUser_depart(Integer.parseInt(user_depart));


        boolean res = userService.updateInfoAdmin(user);
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
     * @Description: POST方法
     *     用户修改密码
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/6/23
     */

    @RequestMapping(value = "updatepasswd", method = RequestMethod.PUT)
    public Object updatePasswd(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String user_phone = req.getParameter("user_phone").trim();
        String user_oldpasswd = req.getParameter("user_oldpasswd").trim();
        String user_passwd = req.getParameter("user_passwd").trim();

        MD5 md = new MD5();
        user_oldpasswd=md.start(user_oldpasswd);
        boolean res=false;

        User user1=userService.getUserOfPhone(user_phone);//获取这个用户原先的密码
        if(user1.getUser_passwd().equals(user_oldpasswd)&&user_passwd!=null){ //旧密码是否一致，且新密码不为空
            MD5 md1 = new MD5();
            user_passwd=md1.start(user_passwd);

            User user = new User();
            user.setUser_phone(user_phone);
            user.setUser_passwd(user_passwd);

            res = userService.updatePasswd(user);
        }
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
     * @Description: POST方法
     *     管理员修改密码
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/6/23
     */

    @RequestMapping(value = "admin/updatepasswd", method = RequestMethod.PUT)
    public Object updatePasswdAdmin(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String user_phone = req.getParameter("user_phone").trim();
        String user_passwd = req.getParameter("user_passwd").trim();
        boolean res=false;
        if(user_passwd!=null){
        MD5 md = new MD5();
        user_passwd=md.start(user_passwd);

        User user = new User();
        user.setUser_phone(user_phone);
        user.setUser_passwd(user_passwd);

        res = userService.updatePasswd(user);
        }
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
     * @Description: POST方法
     *     管理员修改权限
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/6/23
     */

    @RequestMapping(value = "updateauth", method = RequestMethod.PUT)
    public Object updateAuth(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String user_phone = req.getParameter("user_phone").trim();
        String user_auth = req.getParameter("user_auth").trim();

        User user = new User();
        user.setUser_phone(user_phone);
        user.setUser_auth(Integer.parseInt(user_auth));


        boolean res = userService.updateAuth(user);
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
     * @Description: GET法
     *     超级管理员查看所有用户
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/6/23
     */
    @RequestMapping(value = "getalluser",method = RequestMethod.GET)
    public Object getAllUser(){
        return userService.getAllUser();
    }

    /**
     * @Description: GET法
     *     管理员查看所有用户
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/6/23
     */
    @RequestMapping(value = "admin/getuser", method = RequestMethod.GET)
    public Object getUser(HttpServletRequest req){
        String user_company = req.getParameter("user_company");
        return userService.getUser(Integer.parseInt(user_company));

    }


    /**
     * @Description: GET法
     *     获得用户权限
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/6/23
     */
    @RequestMapping(value = "getauth",method = RequestMethod.GET)
    public Object getAuth(HttpServletRequest req){
        String user_phone = req.getParameter("user_phone");
        User user = userService.getUserOfPhone(user_phone);
        return user.getUser_auth();
    }

    /**
     * @Description: GET法
     *     用户获取个人信息
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/6/23
     */
    @RequestMapping(value = "/getuserofphone", method = RequestMethod.GET)
    public Object getUserOfPhone(HttpServletRequest req){
        String user_phone = req.getParameter("user_phone");
        return userService.getUserOfPhone(user_phone);

    }

    /**
     * @Description: GET法
     *     管理员删除用户，用户注销
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/6/23
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Object deleteUser(HttpServletRequest req){
        String user_phone = req.getParameter("user_phone");
        boolean res=false;
        if(user_phone!=null){
            res = userService.deleteUser(user_phone);
        }
        JSONObject jsonObject = new JSONObject();
        if (res){
        jsonObject.put(Consts.CODE, 1);
        jsonObject.put(Consts.MSG, "删除成功");
        return jsonObject;
    }else {
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "删除失败");
        return jsonObject;
    }
    }




}


