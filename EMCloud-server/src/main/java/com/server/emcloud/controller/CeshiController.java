package com.server.emcloud.controller;

import com.server.emcloud.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//import java.io.FileOutputStream;
//
//import javax.json.Json;
//
//import javax.json.JsonArray;
//
//import javax.json.JsonWriter;

@RestController
public class CeshiController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "getEquipState",method = RequestMethod.GET)
    public Object a(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();

        String json = "[{'equipstate':1,'count':55},{'equipstate':0,'count':60}]";
        JSONArray jsonArray = JSONArray.fromObject(json);

        jsonObject.put("list", jsonArray);

        return jsonObject;
    }
    @RequestMapping(value = "getEquipExType",method = RequestMethod.GET)
    public Object ab(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        System.out.println("nihao");

        String json = "[{'exception_name':'achanpin','exception_num':55},{'exception_name':'bchanpin','exception_num':75},{'exception_name':'cchanpin','exception_num':100}]";
        JSONArray jsonArray = JSONArray.fromObject(json);
        jsonObject.put("list", jsonArray);

        return jsonObject;
    }

    @RequestMapping(value = "getExceptionByDay",method = RequestMethod.GET)
    public Object abc(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        System.out.println("hhhhh");

        String json = "[{'time':'2022-06-15','count':100},{'time':'2022-06-16','count':100},{'time':'2022-06-18','count':55},{'time':'2022-06-19','count':45},{'time':'2022-06-22','count':200},{'time':'2022-06-24','count':40},{'time':'2022-06-25','count':100},{'time':'2022-06-26','count':100},{'time':'2022-06-27','count':100}]";
        JSONArray jsonArray = JSONArray.fromObject(json);
        jsonObject.put("list", jsonArray);

        return jsonObject;
    }
    @RequestMapping(value = "getExceptionByMouth",method = RequestMethod.GET)
    public Object abcd(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        System.out.println("aaa");

        String json = "[{'time':'2021-06','count':100},{'time':'2021-07','count':100},{'time':'2021-08','count':55},{'time':'2021-10','count':45},{'time':'2022-01','count':200}]";
        JSONArray jsonArray = JSONArray.fromObject(json);
        jsonObject.put("list", jsonArray);

        return jsonObject;
    }
    @RequestMapping(value = "getExceptionTypeByDay",method = RequestMethod.GET)
    public Object abcc(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        System.out.println("lalalalala");

        String json = "[{'name':'a','data':[{'time':'2022-06-15','count':100},{'time':'2022-06-16','count':100},{'time':'2022-06-18','count':55},{'time':'2022-06-19','count':45},{'time':'2022-06-22','count':200}, {'time':'2022-06-24','count':40},{'time':'2022-06-26','count':50},{'time':'2022-06-28','count':150}]},{'name':'b','data':[{'time':'2022-06-16','count':10},{'time':'2022-06-17','count':110},{'time':'2022-06-18','count':50},{'time':'2022-06-100','count':45},{'time':'2022-06-22','count':190}, {'time':'2022-06-24','count':40},{'time':'2022-06-26','count':70},{'time':'2022-06-28','count':100}]}]" ;
        JSONArray jsonArray = JSONArray.fromObject(json);
        jsonObject.put("list", jsonArray);

        return jsonObject;
    }
    @RequestMapping(value = "getExceptionTypeByMouth",method = RequestMethod.GET)
    public Object abcdc(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        System.out.println("aaa");

        String json = "[{'name':'a','data':[{'time':'2022-01','count':100},{'time':'2022-02','count':100},{'time':'2022-03','count':55},{'time':'2022-04','count':45},{'time':'2022-05','count':200}]},{'name':'b','data':[{'time':'2022-01','count':50},{'time':'2022-02','count':120},{'time':'2022-03','count':20},{'time':'2022-04','count':65},{'time':'2022-05','count':170}]}]";
        JSONArray jsonArray = JSONArray.fromObject(json);
        jsonObject.put("list", jsonArray);

        return jsonObject;
    }


    @RequestMapping(value = "getEquipTaskType",method = RequestMethod.GET)
    public Object e(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        System.out.println("nihao");

        String json = "[{'exception_name':'achanpin','exception_num':10},{'exception_name':'bchanpin','exception_num':150},{'exception_name':'cchanpin','exception_num':100}]";
        JSONArray jsonArray = JSONArray.fromObject(json);
        jsonObject.put("list", jsonArray);

        return jsonObject;
    }

    @RequestMapping(value = "getTaskByDay",method = RequestMethod.GET)
    public Object abcf(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        System.out.println("hhhhh");

        String json = "[{'time':'2022-06-15','count':100},{'time':'2022-06-16','count':100},{'time':'2022-06-18','count':55},{'time':'2022-06-19','count':45},{'time':'2022-06-22','count':200},{'time':'2022-06-24','count':40},{'time':'2022-06-25','count':100},{'time':'2022-06-26','count':100},{'time':'2022-06-27','count':100}]";
        JSONArray jsonArray = JSONArray.fromObject(json);
        jsonObject.put("list", jsonArray);

        return jsonObject;
    }
    @RequestMapping(value = "getTaskByMouth",method = RequestMethod.GET)
    public Object abcdg(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        System.out.println("aaa");

        String json = "[{'time':'2021-06','count':100},{'time':'2021-07','count':100},{'time':'2021-08','count':55},{'time':'2021-10','count':45},{'time':'2022-01','count':200}]";
        JSONArray jsonArray = JSONArray.fromObject(json);
        jsonObject.put("list", jsonArray);

        return jsonObject;
    }
    @RequestMapping(value = "getTaskTypeByDay",method = RequestMethod.GET)
    public Object abcch(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        System.out.println("lalalalala");

        String json = "[{'name':'a','data':[{'time':'2022-06-18','count':55},{'time':'2022-06-19','count':45},{'time':'2022-06-22','count':200}, {'time':'2022-06-24','count':40},{'time':'2022-06-26','count':50},{'time':'2022-06-28','count':150}]},{'name':'b','data':[{'time':'2022-06-16','count':10},{'time':'2022-06-17','count':110},{'time':'2022-06-18','count':50},{'time':'2022-06-100','count':45},{'time':'2022-06-22','count':190}, {'time':'2022-06-24','count':40},{'time':'2022-06-26','count':80},{'time':'2022-06-28','count':120}]}]" ;
        JSONArray jsonArray = JSONArray.fromObject(json);
        jsonObject.put("list", jsonArray);

        return jsonObject;
    }
    @RequestMapping(value = "getTaskTypeByMouth",method = RequestMethod.GET)
    public Object abcdci(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        System.out.println("aaa");

        String json = "[{'name':'a','data':[{'time':'2022-01','count':100},{'time':'2022-02','count':100},{'time':'2022-03','count':55},{'time':'2022-04','count':45},{'time':'2022-05','count':200}]},{'name':'b','data':[{'time':'2022-01','count':50},{'time':'2022-02','count':120},{'time':'2022-03','count':20},{'time':'2022-04','count':65},{'time':'2022-05','count':170}]}]";
        JSONArray jsonArray = JSONArray.fromObject(json);
        jsonObject.put("list", jsonArray);

        return jsonObject;
    }





}
