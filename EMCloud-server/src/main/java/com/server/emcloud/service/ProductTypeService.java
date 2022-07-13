package com.server.emcloud.service;

import com.server.emcloud.domain.ProductType;
import com.server.emcloud.domain.ProductTypeAttribute;
import com.server.emcloud.vo.ProductTypeAndTaskNum;
import com.server.emcloud.vo.ProductTypeExceptionNum;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/06/23/16:10
 * @Description:
 */
public interface ProductTypeService {

    //查询全部的产品类型信息
    public List<ProductType> getAllProductType();
    //产品类型添加
    public int addProductType(ProductType productType);
    //产品类型信息更新
    public int updateProductType(ProductType productType);

    List<ProductTypeExceptionNum> getAllProductTypeExceptionNum();

    List<ProductTypeExceptionNum> getAllProductTypeExceptionNum2(String company_id);

    //查询不同类型产品的任务数量
    public List<ProductTypeAndTaskNum> getProductTypeAndTaskNum();
   //查询某公司不同类型产品的任务数量
    public List<ProductTypeAndTaskNum> getProductTypeAndTaskNum2(int company_id);
}
