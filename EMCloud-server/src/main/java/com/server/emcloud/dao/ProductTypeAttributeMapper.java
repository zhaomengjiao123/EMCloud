package com.server.emcloud.dao;

import com.server.emcloud.domain.ProductTypeAttribute;
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
    //更新产品类型的属性
    public int updateProductTypeAttribute(ProductTypeAttribute productTypeAttribute);
    //删除某个产品类型的属性
    public int deleteProductTypeAttributeOfId(int product_attribute_id);


}
