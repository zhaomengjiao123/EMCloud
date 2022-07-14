package com.server.emcloud.service;

import com.server.emcloud.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/06/23/10:59
 * @Description: product 服务类
 */
public interface ProductService {

    //查询全部的产品信息
    public List<Product> getAllProduct();

    //添加产品信息
    public int addProduct(Product product);
    //更新产品信息
    public int updateProduct(Product product);

    public int deleteProduct(String product_id);
}
