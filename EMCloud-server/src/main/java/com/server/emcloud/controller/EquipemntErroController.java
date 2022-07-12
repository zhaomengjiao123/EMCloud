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

    /**
    * @Description: 请求得到每个城市的报警数量
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/7/5
    */
    @RequestMapping(value = "/getErroCountOfAllCity",method = RequestMethod.GET)
    public Object getErroCountOfAllCity(HttpServletRequest request){
        System.out.println("请求得到每个城市的报警数量");
        return equipmentErroService.getErroCountOfAllCity();
    }


    /**
    * @Description: 请求得到某公司的全部异常信息
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/7/6
    */
    @RequestMapping(value = "/getAllExceptionInfoByCid",method = RequestMethod.GET)
    public Object getAllExceptionInfoByCid(HttpServletRequest request){
        String company_id = request.getParameter("company_id");
        System.out.println("请求得到某公司的全部异常信息："+company_id);
        return equipmentErroService.getAllExceptionInfoByCid(new Integer(company_id));
    }

    /**
    * @Description: 请求得到全部异常信息
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/7/6
    */
    @RequestMapping(value = "/getAllExceptionInfo",method = RequestMethod.GET)
    public Object getAllExceptionInfo(HttpServletRequest request){
        System.out.println("请求得到全部异常信息");
        return equipmentErroService.getAllExceptionInfo();
    }

    /**
    * @Description: 请求得到全部报警信息
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/7/6
    */
    @RequestMapping(value = "/getAllErroInfo",method = RequestMethod.GET)
    public Object getAllErroInfo(HttpServletRequest request){
        System.out.println("请求得到全部报警信息");
        return equipmentErroService.getAllErroInfo();
    }

    /**
    * @Description: 根据设备编号查询设备的报警信息
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/7/12
    */
    @RequestMapping(value = "/getErroByEquipmentNumberAndCid",method = RequestMethod.GET)
    public Object getErroByEquipmentNumberAndCid(HttpServletRequest request){
        String company_id = request.getParameter("company_id");
        String equipment_number = request.getParameter("equipment_number");
        System.out.println("根据设备编号查询设备的报警信息");
        return equipmentErroService.getErroByEquipmentNumberAndCid(new Integer(company_id),equipment_number);
    }
}
