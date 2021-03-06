package com.server.emcloud.controller;

import com.server.emcloud.service.EquipmentWarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/01/1:14
 * @Description:
 */
@RestController
@RequestMapping("/equipmentWarning")
public class EquipmentWarningController {

    @Autowired
    private EquipmentWarningService equipmentWarningService;

    /**
    * @Description: 根据公司的D得到某公司的所有设备的预警信息
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/7/1
    */
    @RequestMapping(value = "/getWarningByCid",method = RequestMethod.GET)
    public Object getWarningByCid(HttpServletRequest request){
        String company_id = request.getParameter("company_id");
        System.out.println("请求得到某公司的所有设备的预警信息："+company_id);
        return equipmentWarningService.getWarningByCid(new Integer(company_id));
    }

    /**
    * @Description: 请求所有设备的预警信息
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/7/6
    */
    @RequestMapping(value = "/getAllWarningInfo",method = RequestMethod.GET)
    public Object getAllWarningInfo(HttpServletRequest request){
        System.out.println("请求所有设备的预警信息");
        return equipmentWarningService.getAllWarningInfo();
    }


    /**
    * @Description: 根据设备编号查询设备的预警信息
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/7/12
    */
    @RequestMapping(value = "/getWarningByEquipmentNumberAndCid",method = RequestMethod.GET)
    public Object getWarningByEquipmentNumberAndCid(HttpServletRequest request){
        String company_id = request.getParameter("company_id");
        String equipment_number = request.getParameter("equipment_number");
        System.out.println("根据设备编号查询设备的预警信息");
        return equipmentWarningService.getWarningByEquipmentNumberAndCid(new Integer(company_id),equipment_number);
    }
}
