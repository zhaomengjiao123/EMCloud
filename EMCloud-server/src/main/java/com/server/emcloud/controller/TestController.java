package com.server.emcloud.controller;


import com.server.emcloud.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;


    /**
    * @Description: GET方法
     *     测试：访问  https://h54k741242.oicp.vip/test/getAll/detail?id=1
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/6/22
    */
    @RequestMapping(value = "/getAll/detail",method = RequestMethod.GET)
    //@RequestMapping("/test") @RequestParam("id") int id
    public Object all(HttpServletRequest request){
        System.out.println(request.getParameter("id"));
        System.out.println("得到全部用户"+testService.all());
        return testService.all();
    }
}
