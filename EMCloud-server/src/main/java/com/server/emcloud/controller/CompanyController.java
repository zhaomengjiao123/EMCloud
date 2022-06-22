package com.server.emcloud.controller;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/06/22/15:48
 * @Description: company 控制类
 */

import com.server.emcloud.domain.Company;
import com.server.emcloud.service.CompanyService;
import com.server.emcloud.utils.Consts;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    /**
    * @Description: 查询全部的公司信息
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/6/22
    */
    @RequestMapping(value = "/getAllCompany",method = RequestMethod.GET)
    public Object getAllCompany(HttpServletRequest request){
        System.out.println("请求得到全部的公司信息");
        System.out.println("得到全部公司"+companyService.getAllCompany());
        return companyService.getAllCompany();
    }


    /**
    * @Description: 添加公司
    * @Param: [req]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/6/22
    */
    @RequestMapping(value = "/addCompany", method = RequestMethod.POST)
    public Object addCompany(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String company_name = req.getParameter("company_name").trim();
        String company_type = req.getParameter("company_type").trim();
        String company_local = req.getParameter("company_local").trim();
        String company_comment = req.getParameter("company_comment").trim();
        System.out.println("添加公司："+company_name);

        //判空
        if(company_name.isEmpty() || company_type.isEmpty() || company_local.isEmpty()){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "添加失败");
            return jsonObject;
        }

        //新建公司对象
        Company company = new Company();
        company.setCompany_name(company_name);        
        company.setCompany_type(company_type);
        company.setCompany_local(company_local);
        company.setCompany_comment(company_comment);
        int res = companyService.addCompany(company);
        
        if (res == 1){
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
    * @Description: 删除公司
    * @Param: [req]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/6/22
    */
    @RequestMapping(value = "/deleteCompany/detail", method = RequestMethod.GET)
    public Object deleteOrder(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String company_id = req.getParameter("company_id");
        System.out.println("要删除的公司ID是"+company_id);

        int res = companyService.deleteCompanyOfCid(new Integer(company_id));

        if (res == 1){
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
