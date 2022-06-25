package com.server.emcloud.service.impl;

import com.server.emcloud.dao.ProductMapper;
import com.server.emcloud.domain.Product;
import com.server.emcloud.service.ProductService;
//import com.sun.org.apache.bcel.internal.generic.ARETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/06/23/11:01
 * @Description: product 服务实现类
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    //查询全部的产品信息
    @Override
    public List<Product> getAllProduct() {
        return productMapper.getAllProduct();
    }

    //添加产品信息
    @Override
    public int addProduct(Product product) {
        return productMapper.addProduct(product)>0 ? 1 : 0;
    }

    //更新产品信息
    @Override
    public int updateProduct(Product product) {
        return productMapper.updateProduct(product)>0 ? 1 : 0;
    }
}
