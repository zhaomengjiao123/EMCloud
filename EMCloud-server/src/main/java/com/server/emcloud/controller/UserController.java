package com.server.emcloud.controller;

import com.server.emcloud.domain.User;
import com.server.emcloud.service.UserService;
import com.server.emcloud.utils.Consts;
import com.server.emcloud.utils.MD5;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public Object loginStatus(HttpServletRequest request,HttpSession session) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        String user_phone = request.getParameter("user_phone").trim();
        String user_passwd = request.getParameter("user_passwd" ).trim();
        MD5 md = new MD5();
        user_passwd=md.start(user_passwd);
        boolean flag=userService.login(user_phone,user_passwd);//是否登陆成功
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"登陆成功");
            session.setAttribute(Consts.NAME,user_phone);
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"用户名或密码错误");
        return jsonObject;
        //return flag;
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
        String user_phone = req.getParameter("user_phone").trim();
        String user_tele = req.getParameter("user_tele").trim();
        String user_company = req.getParameter("user_company").trim();
        String user_depart = req.getParameter("user_depart").trim();
        String user_passwd = req.getParameter("user_passwd").trim();
        String user_auth = req.getParameter("user_auth").trim();


        MD5 md = new MD5();
        user_passwd=md.start(user_passwd);

        User user = new User();
        user.setUser_name(user_name);
        user.setUser_phone(user_phone);
        user.setUser_tele(user_tele);
        user.setUser_company(Integer.parseInt(user_company));
        user.setUser_depart(Integer.parseInt(user_depart));
        user.setUser_passwd(user_passwd);
        user.setUser_auth(Integer.parseInt(user_auth));

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

}

