package com.server.emcloud.service.impl;

import com.server.emcloud.dao.ProductTypeMapper;
import com.server.emcloud.domain.Product;
import com.server.emcloud.domain.ProductType;
import com.server.emcloud.service.ProductService;
import com.server.emcloud.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/06/23/16:11
 * @Description:
 */
@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeMapper productTypeMapper;

    //得到全部的产品类型信息
    @Override
    public List<ProductType> getAllProductType() {
        return productTypeMapper.getAllProductType();
    }

    @Override
    public int addProductType(ProductType productType) {
        return productTypeMapper.addProductType(productType)>0 ? 1 : 0;
    }

    @Override
    public int updateProductType(ProductType productType) {
        return productTypeMapper.updateProductType(productType)>0 ? 1 : 0;
    }


}
