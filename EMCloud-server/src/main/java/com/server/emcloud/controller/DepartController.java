package com.server.emcloud.controller;

import com.server.emcloud.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/depart")
public class DepartController {

    @Autowired
    private DepartService departService;
    /**
     * @Description: GET法
     *     获取全部部门
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/7/3
     */
    @RequestMapping(value = "getalldepart",method = RequestMethod.GET)
    public Object getAllDepart(){
        return departService.getAllDepart();
    }

    /**
     * @Description: GET法
     *     获取某个公司部门
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/7/3
     */
    @RequestMapping(value = "getdepartbycompany",method = RequestMethod.GET)
    public Object getDepartByCompany(HttpServletRequest req){
        String company_id = req.getParameter("company_id");
        return departService.getDepartByCompany(Integer.parseInt(company_id));
    }


}
