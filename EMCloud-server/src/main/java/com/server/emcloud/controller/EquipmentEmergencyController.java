package com.server.emcloud.controller;

import com.server.emcloud.service.EquipmentEmergencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/01/10:15
 * @Description:
 */
@RestController
@RequestMapping("/equipmentEmergency")
public class EquipmentEmergencyController {

    @Autowired
    private EquipmentEmergencyService equipmentEmergencyService;

    /**
    * @Description: 请求得到某公司的所有设备的紧急警告信息
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/7/1
    */
    @RequestMapping(value = "/getEmergencyByCid",method = RequestMethod.GET)
    public Object getEmergencyByCid(HttpServletRequest request){
        String company_id = request.getParameter("company_id");
        System.out.println("请求得到某公司的所有设备的紧急警告信息："+company_id);
        return equipmentEmergencyService.getEmergencyByCid(new Integer(company_id));
    }

    /**
    * @Description: 请求所有设备的紧急警告信息
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/7/6
    */
    @RequestMapping(value = "/getAllEmergencyInfo",method = RequestMethod.GET)
    public Object getAllEmergencyInfo(HttpServletRequest request){
        System.out.println("请求所有设备的紧急警告信息");
        return equipmentEmergencyService.getAllEmergencyInfo();
    }
}
