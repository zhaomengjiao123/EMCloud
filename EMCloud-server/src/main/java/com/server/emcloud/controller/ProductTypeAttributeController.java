package com.server.emcloud.controller;

import com.server.emcloud.domain.ProductType;
import com.server.emcloud.domain.ProductTypeAttribute;
import com.server.emcloud.service.ProductTypeAttributeService;
import com.server.emcloud.utils.Consts;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/06/23/19:11
 * @Description:
 */
@RestController
@RequestMapping("/productTypeAttribute")
public class ProductTypeAttributeController {

    @Autowired
    private ProductTypeAttributeService productTypeAttributeService;

    /**
    * @Description: 查询全部的产品类型的属性信息
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/6/23
    */
    @RequestMapping(value = "/getAllProductTypeAttribute",method = RequestMethod.GET)
    public Object getAllProductTypeAttribute(HttpServletRequest request){
        System.out.println("");
        System.out.println("请求得到全部的产品类型属性信息："+productTypeAttributeService.getAllProductTypeAttribute());
        return productTypeAttributeService.getAllProductTypeAttribute();
    }

    /**
    * @Description: 给某产品类型添加属性 属性名称 对应的产品类型ID 属性编码 属性更新时间必需 属性备注非必须
    * @Param: [productTypeAttribute]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/6/23
    */
    @RequestMapping(value = "/addProductTypeAttribute", method = RequestMethod.POST)
    public Object addProductTypeAttribute(@RequestBody ProductTypeAttribute productTypeAttribute){
        JSONObject jsonObject = new JSONObject();
        System.out.println("要添加的某产品类型的属性信息是："+productTypeAttribute.getProduct_attribute_name()+", "+
                productTypeAttribute.getProduct_type_id());

        int res = productTypeAttributeService.addProductTypeAttribute(productTypeAttribute);

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
    * @Description: 更新产品属性 目前支持的是更新 产品属性名称/更新时间/属性编码/属性备注
    * @Param: [productTypeAttribute]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/6/24
    */
    @RequestMapping(value = "/updateProductTypeAttribute", method = RequestMethod.POST)
    public Object updateProductTypeAttribute(@RequestBody ProductTypeAttribute productTypeAttribute){
        JSONObject jsonObject = new JSONObject();
        System.out.println("要更新的某产品类型的属性信息是："+productTypeAttribute.getProduct_attribute_id()+", "+
                productTypeAttribute.getProduct_attribute_name());

        int res = productTypeAttributeService.updateProductTypeAttribute(productTypeAttribute);

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

    /**
    * @Description: 根据属性ID删除某个属性
    * @Param: [req]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/6/24
    */
    @RequestMapping(value = "/deleteProductTypeAttribute/detail", method = RequestMethod.GET)
    public Object deleteCompany(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String product_attribute_id = req.getParameter("product_attribute_id");
        System.out.println("要删除的属性ID是："+product_attribute_id);

        int res = productTypeAttributeService.deleteProductTypeAttributeOfId(new Integer(product_attribute_id));

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
