package com.server.emcloud.dao;

import com.server.emcloud.domain.ProductTypeAttribute;
import com.server.emcloud.vo.ProductTypeAttributeVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/06/23/17:12
 * @Description: 产品类型属性
 */
@Repository
public interface ProductTypeAttributeMapper {

    //查询全部的产品类型的属性
    public List<ProductTypeAttribute> getAllProductTypeAttribute();
    //添加某产品类型的属性
    public int addProductTypeAttribute(ProductTypeAttribute productTypeAttribute);
    //更新产品类型的属性  名字编号
    public int updateProductTypeAttribute(ProductTypeAttribute productTypeAttribute);
    //删除某个产品类型的属性
    public int deleteProductTypeAttributeOfId(int product_attribute_id);
    //查询全部属性+对应的产品名 产品属性值列表
    public List<ProductTypeAttributeVO> getProductTypeAttributeAndProductName();
    //根据属性ID更新属性的各种值
    public int updateProductTypeAttributeValues(ProductTypeAttribute productTypeAttribute);
    //根据产品ID查询这个产品的所有属性
    public List<ProductTypeAttributeVO> getProductTypeAttributeAndProductNameOfPid(int product_id);


}
