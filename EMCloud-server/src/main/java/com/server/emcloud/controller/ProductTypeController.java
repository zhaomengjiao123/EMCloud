package com.server.emcloud.controller;

import com.server.emcloud.domain.Product;
import com.server.emcloud.domain.ProductType;
import com.server.emcloud.service.ProductTypeService;
import com.server.emcloud.utils.Consts;
import com.server.emcloud.vo.ProductTypeExceptionNum;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/06/23/16:15
 * @Description:
 */
@RestController
@RequestMapping("/productType")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    /**
    * @Description: 查询全部的产品类型信息
    * @Param: [req]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/6/23
    */
    @RequestMapping(value = "/getAllProductType", method = RequestMethod.GET)
    public Object getAllProductType(HttpServletRequest req){
        System.out.println("请求查询全部的产品类型信息："+productTypeService.getAllProductType());
        return productTypeService.getAllProductType();
    }

    /**
    * @Description: 添加产品类型信息 名称 编码必填 更新时间必填 备注可以不填
    * @Param: [productType]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/6/23
    */
    @RequestMapping(value = "/addProductType", method = RequestMethod.POST)
    public Object addProductType(@RequestBody ProductType productType){
        JSONObject jsonObject = new JSONObject();
        System.out.println("要添加的产品类型信息是："+productType.getProduct_type_name()+", "+
                productType.getProduct_type_number());
        int res = productTypeService.addProductType(productType);

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
    * @Description: 更新产品类型信息
    * @Param: [productType]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/6/23
    */
    @RequestMapping(value = "/updateProductType", method = RequestMethod.POST)
    public Object updateProductType(@RequestBody ProductType productType){
        JSONObject jsonObject = new JSONObject();
        System.out.println("更新的产品类型ID是："+productType.getProduct_type_id());
        int res = productTypeService.updateProductType(productType);

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
     * author：王俊博
     * 查询不同类型产品的异常数量
     */
    @GetMapping("/getAllProductTypeExceptionNum")
    public List<ProductTypeExceptionNum> getAllProductTypeExceptionNum(){
        return productTypeService.getAllProductTypeExceptionNum();
    }

}
