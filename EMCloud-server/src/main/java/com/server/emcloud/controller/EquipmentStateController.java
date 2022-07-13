package com.server.emcloud.controller;

import com.server.emcloud.service.EquipmentStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/13/23:45
 * @Description:
 */
@RestController
@RequestMapping("/equipmentState")
public class EquipmentStateController {

    @Autowired
    private EquipmentStateService equipmentStateService;

    /**
    * @Description: 请求得到所有设备的更新列表
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/7/14
    */
    @RequestMapping(value = "/getEquipmentListOfUpdateTime",method = RequestMethod.GET)
    public Object getEquipmentListOfUpdateTime(HttpServletRequest request){
        System.out.println("请求得到所有设备的更新列表");
        return equipmentStateService.getEquipmentListOfUpdateTime();
    }

    /**
    * @Description: 请求得到juti设备的实时状态信息
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/7/14
    */
    @RequestMapping(value = "/getStateInfoByEidAndTime",method = RequestMethod.GET)
    public Object getStateInfoByEidAndTime(HttpServletRequest request){
        String equipment_id = request.getParameter("equipment_id");
        String state_update_time = request.getParameter("state_update_time");

        System.out.println("请求得到juti设备的实时状态信息"+equipment_id+" "+state_update_time);
        return equipmentStateService.getStateInfoByEidAndTime(new Integer(equipment_id),state_update_time);
    }

    /**
    * @Description: 请求得到某公司的设备的实时更新时间
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/7/14
    */
    @RequestMapping(value = "/getEquipmentListOfUpdateTimeByCid",method = RequestMethod.GET)
    public Object getEquipmentListOfUpdateTimeByCid(HttpServletRequest request){
        String company_id = request.getParameter("company_id");
        System.out.println("请求得到某公司的设备的实时更新时间"+" "+company_id);
        return equipmentStateService.getEquipmentListOfUpdateTimeByCid(new Integer(company_id));
    }

    /**
    * @Description: 请求得到某公司的某编号设备的实时更新时间
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/7/14
    */
    @RequestMapping(value = "/getEquipmentListOfUpdateTimeByCidAndEn",method = RequestMethod.GET)
    public Object getEquipmentListOfUpdateTimeByCidAndEn(HttpServletRequest request){
        String company_id = request.getParameter("company_id");
        String equipment_number = request.getParameter("equipment_number");
        System.out.println("请求得到某公司的某编号设备的实时更新时间"+" "+company_id+" "+equipment_number);
        return equipmentStateService.getEquipmentListOfUpdateTimeByCidAndEn(new Integer(company_id),equipment_number);
    }




}
