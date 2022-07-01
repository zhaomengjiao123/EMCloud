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
import org.springframework.web.bind.annotation.*;

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
        String company_local_province = req.getParameter("company_local_province").trim();
        String company_local_city = req.getParameter("company_local_city").trim();

        String company_comment = req.getParameter("company_comment").trim();
        System.out.println("添加公司："+company_name);

        //判空
        if(company_name.isEmpty() || company_type.isEmpty() || company_local_city.isEmpty()||company_local_province.isEmpty()){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "添加失败");
            return jsonObject;
        }

        //新建公司对象
        Company company = new Company();
        company.setCompany_name(company_name);        
        company.setCompany_type(company_type);
        company.setCompany_local_province(company_local_province);
        company.setCompany_local_city(company_local_city);
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
    @RequestMapping(value = "/deleteCompany/detail", method = RequestMethod.DELETE)
    public Object deleteCompany(HttpServletRequest req){
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


    /**
     * @Description: 更新公司，可以传入部分参数，只更新传入的参数。主键必须传入。
     * @Param: company对象的json格式字符串，比如：{"company_id":"2022","company_name":"test2"}
     * @return: java.lang.Object
     * @Author: 王俊博
     * @Date: 2022/6/22
     */

    @PostMapping("/update")
    public Object updateByPrimaryKeySelective(@RequestBody Company record) {
        System.out.println("要更新公司的id是："+record.getCompany_id());

        JSONObject jsonObject = new JSONObject();
        int res = companyService.updateByPrimaryKeySelective(record);
        if (res == 1){
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "更新成功");
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "更新失败");
            return jsonObject;
        }
    }

}
