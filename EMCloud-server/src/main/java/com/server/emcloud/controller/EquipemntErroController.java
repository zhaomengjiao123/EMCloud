package com.server.emcloud.controller;

import com.server.emcloud.service.EquipmentErroService;
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
 * @Date: 2022/07/01/9:33
 * @Description:
 */
@RestController
@RequestMapping("/equipmentErro")
public class EquipemntErroController {

    @Autowired
    private EquipmentErroService equipmentErroService;

    /**
    * @Description: 请求得到某公司的所有设备的报警信息
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/7/1
    */
    @RequestMapping(value = "/getErroByCid",method = RequestMethod.GET)
    public Object getErroByCid(HttpServletRequest request){
        String company_id = request.getParameter("company_id");
        System.out.println("请求得到某公司的所有设备的报警信息："+company_id);
        return equipmentErroService.getErroByCid(new Integer(company_id));
    }
}
