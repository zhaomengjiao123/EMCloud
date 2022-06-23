package com.server.emcloud.controller;

import com.server.emcloud.domain.Product;
import com.server.emcloud.service.ProductService;
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
 * @Date: 2022/06/23/11:03
 * @Description: product 控制类
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
    * @Description: 得到全部的产品信息
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/6/23
    */
    @RequestMapping(value = "/getAllProduct",method = RequestMethod.GET)
    public Object getAllProduct(HttpServletRequest request){
        System.out.println("请求得到全部的产品信息");
        return productService.getAllProduct();
    }

    /**
    * @Description: 添加产品
    * @Param: [product]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/6/23
    */
    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public Object addProduct(@RequestBody Product product){
        JSONObject jsonObject = new JSONObject();

        System.out.println("要添加的产品信息是："+product.getProduct_name()+product.getProduct_number());

        int res = productService.addProduct(product);

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
    * @Description: 根据产品ID 更新产品信息  更新产品类型 产品编号 产品型号 更新时间
    * @Param: [product]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/6/24
    */
    @RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
    public Object updateProduct(@RequestBody Product product){
        JSONObject jsonObject = new JSONObject();

        System.out.println("要更新的产品信息是："+product.getProduct_name()+product.getProduct_id());

        int res = productService.updateProduct(product);

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
