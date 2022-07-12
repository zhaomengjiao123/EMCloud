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
import java.util.IdentityHashMap;
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
    /**
    * @Description: 修改
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/7/11
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
            System.out.println(user_passwd);
            flag = userService.login(user_phone, user_passwd);//是否登陆成功
            System.out.println("ff:"+flag);
        }

        if(flag){
            User user=userService.getUserOfPhone(user_phone);
            String user_name=user.getUser_name();
            Integer user_auth=user.getUser_auth();
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"登陆成功");
            jsonObject.put("user_name",user_name);
            jsonObject.put("user_phone",user_phone);
            jsonObject.put(Consts.AUTH,user_auth);
            jsonObject.put("company_id",user.getCompany_id());


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
//        String user_company = req.getParameter("user_company").trim();
//        String user_depart = req.getParameter("user_depart").trim();
        String company_id = req.getParameter("company_id").trim();
        String depart_id = req.getParameter("depart_id").trim();
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
            user.setCompany_id(Integer.parseInt(company_id));
            user.setDepart_id(Integer.parseInt(depart_id));
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
//        String user_company = req.getParameter("user_company").trim();
//        String user_depart = req.getParameter("user_depart").trim();
        String company_id = req.getParameter("company_id").trim();
        String depart_id = req.getParameter("depart_id").trim();
        String user_passwd = "123456";//默认密码123456

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
//        user.setCompany_id(Integer.parseInt(company_id));
//        user.setDepart_id(Integer.parseInt(depart_id));
        user.setCompany_id(Integer.parseInt(company_id));
        user.setDepart_id(Integer.parseInt(depart_id));
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
//        String user_company = req.getParameter("user_company").trim();
//        String user_depart = req.getParameter("user_depart").trim();
        String company_id = req.getParameter("company_id").trim();
        String depart_id = req.getParameter("depart_id").trim();
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
//        user.setUser_company(Integer.parseInt(user_company));
//        user.setUser_depart(Integer.parseInt(user_depart));
        user.setCompany_id(Integer.parseInt(company_id));
        user.setDepart_id(Integer.parseInt(depart_id));
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
        //String company_id = req.getParameter("company_id").trim();
        String depart_id = req.getParameter("depart_id").trim();

        User user = new User();
        user.setUser_name(user_name);
        user.setUser_phone(user_phone);
        user.setUser_tele(user_tele);
//        user.setUser_company(Integer.parseInt(user_company));
//        user.setUser_depart(Integer.parseInt(user_depart));
        //  user.setCompany_id(Integer.parseInt(company_id));
        user.setDepart_id(Integer.parseInt(depart_id));


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
     *     超级管理员修改信息，可以修改权限
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/7/2
     */
    @RequestMapping(value = "/admin/updateinfosuper", method = RequestMethod.PUT)
    public Object updateInfoSuperAdmin(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String user_name = req.getParameter("user_name").trim();
        String user_phone = req.getParameter("user_phone").trim();
        String user_tele = req.getParameter("user_tele").trim();
        String company_id = req.getParameter("company_id").trim();
        String depart_id = req.getParameter("depart_id").trim();
        String user_auth = req.getParameter("user_auth").trim();

        User user = new User();
        user.setUser_name(user_name);
        user.setUser_phone(user_phone);
        user.setUser_tele(user_tele);
        user.setCompany_id(Integer.parseInt(company_id));
        user.setDepart_id(Integer.parseInt(depart_id));
        user.setUser_auth(Integer.parseInt(user_auth));

        boolean res = userService.updateInfoSuperAdmin(user);
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
     *     管理员重置密码
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/6/23
     */

    @RequestMapping(value = "admin/updatepasswd", method = RequestMethod.PUT)
    public Object updatePasswdAdmin(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String user_phone = req.getParameter("user_phone").trim();
        //String user_passwd = req.getParameter("user_passwd").trim();
        User user=new User();
        user.setUser_phone(user_phone);
        MD5 md = new MD5();
        String user_passwd=md.start("123456");
        user.setUser_passwd(user_passwd);
        boolean res=false;
        res = userService.updatePasswd(user);
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
     *     超级管理员修改权限
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
        String company_id = req.getParameter("company_id");
        return userService.getUser(Integer.parseInt(company_id));

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
     *     超级管理员获取用户信息(模糊)
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/6/23
     */
    @RequestMapping(value = "/getuserofphone2", method = RequestMethod.GET)
    public Object getUserOfPhone3(HttpServletRequest req){
        System.out.println("通过手机号获取用户");
        String user_phone = req.getParameter("user_phone");
        return userService.getUserOfPhone2(user_phone);
    }
    /**
     * @Description: GET法
     *     用户获取个人信息或者超级管理员获取用户信息
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/6/23
     */
    @RequestMapping(value = "/getuserofphone1", method = RequestMethod.GET)
    public Object getUserOfPhone1(HttpServletRequest req){
        System.out.println("通过手机号获取用户");
        String user_phone = req.getParameter("user_phone");
        return userService.getUserOfPhone1(user_phone);
    }


    /**
     * @Description: GET法
     *     管理员获取用户信息
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/6/23
     */
    @RequestMapping(value = "/getuserofcompanyid", method = RequestMethod.GET)
    public Object getUserOfPhone2(HttpServletRequest req){
//        String user_phone = req.getParameter("user_phone");
//        String company_id = req.getParameter("company_id");
//        User user=userService.getUserOfPhone(user_phone);
//        JSONObject jsonObject = new JSONObject();
//        if(user.getCompany_id()==Integer.parseInt(company_id)){
//            return userService.getUserOfPhone1(user_phone);
//        }else{
//            jsonObject.put(Consts.CODE, 0);
//            jsonObject.put(Consts.MSG, "该用户不属于本公司");
//            return jsonObject;
//        }

        String user_phone = req.getParameter("user_phone");
        String company_id = req.getParameter("company_id");
        JSONObject jsonObject = new JSONObject();

            return userService.getUserOfPhone3(user_phone,company_id);

    }
    /**
     * @Description: GET法
     *     管理员删除用户，用户注销
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/6/23
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
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


