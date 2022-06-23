package com.server.emcloud.service.impl;

import com.server.emcloud.dao.ProductTypeAttributeMapper;
import com.server.emcloud.domain.ProductTypeAttribute;
import com.server.emcloud.service.ProductTypeAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/06/23/17:16
 * @Description:
 */
@Service
public class ProductTypeAttributeServiceImpl implements ProductTypeAttributeService {

    @Autowired
    private ProductTypeAttributeMapper productTypeAttributeMapper;

    //查询全部的产品类型的属性
    @Override
    public List<ProductTypeAttribute> getAllProductTypeAttribute() {
        return productTypeAttributeMapper.getAllProductTypeAttribute();
    }

    //添加某产品类型的属性
    @Override
    public int addProductTypeAttribute(ProductTypeAttribute productTypeAttribute) {
        return productTypeAttributeMapper.addProductTypeAttribute(productTypeAttribute)>0 ? 1 : 0;
    }

    @Override
    public int updateProductTypeAttribute(ProductTypeAttribute productTypeAttribute) {
        return productTypeAttributeMapper.updateProductTypeAttribute(productTypeAttribute)>0 ? 1 : 0;
    }

    //根据属性ID删除属性
    public int deleteProductTypeAttributeOfId(int product_attribute_id) {
        return productTypeAttributeMapper.deleteProductTypeAttributeOfId(product_attribute_id)>0 ? 1 : 0;
    }
}
