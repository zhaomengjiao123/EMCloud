package com.server.emcloud.service.impl;

import com.server.emcloud.dao.ProductTypeMapper;
import com.server.emcloud.domain.Product;
import com.server.emcloud.domain.ProductType;
import com.server.emcloud.domain.ProductTypeAttribute;
import com.server.emcloud.service.ProductService;
import com.server.emcloud.service.ProductTypeService;
import com.server.emcloud.vo.ProductTypeAndTaskNum;
import com.server.emcloud.vo.ProductTypeExceptionNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<ProductTypeExceptionNum> getAllProductTypeExceptionNum() {
        return productTypeMapper.getAllProductTypeExceptionNum();
    }

    @Override
    public List<ProductTypeExceptionNum> getAllProductTypeExceptionNum2(String company_id) {
        return productTypeMapper.getAllProductTypeExceptionNum2(company_id);
    }

    //查询不同类型产品的任务数量
    @Override
    public List<ProductTypeAndTaskNum> getProductTypeAndTaskNum() {
        List<ProductTypeAndTaskNum> resultList = new ArrayList<>();
        //获得所有产品类型
        List<ProductType> allProductType = productTypeMapper.getAllProductType();
        //循环每个产品类型
        for (ProductType productType : allProductType) {
            //构建结果
            ProductTypeAndTaskNum result = new ProductTypeAndTaskNum();
            result.setProduct_type(productType.getProduct_type_name());
            result.setTask_num(productTypeMapper.getTaskNumByPid(productType.getProduct_type_id()));
            //加入结果集
            resultList.add(result);
        }
        return resultList;
    }
   //查询某公司不同类型产品的任务数量
    @Override
    public List<ProductTypeAndTaskNum> getProductTypeAndTaskNum2(int company_id) {
        List<ProductTypeAndTaskNum> resultList = new ArrayList<>();
        //获得所有产品类型
        List<ProductType> allProductType = productTypeMapper.getAllProductType();
        //循环每个产品类型
        for (ProductType productType : allProductType) {
            //构建结果
            ProductTypeAndTaskNum result = new ProductTypeAndTaskNum();
            result.setProduct_type(productType.getProduct_type_name());
            result.setTask_num(productTypeMapper.getTaskNumByPidByCid(productType.getProduct_type_id(), company_id));
            //加入结果集
            resultList.add(result);
        }
        return resultList;
    }

}
