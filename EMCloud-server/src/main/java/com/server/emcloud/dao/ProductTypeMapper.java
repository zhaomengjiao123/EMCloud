package com.server.emcloud.dao;

import com.server.emcloud.domain.Product;
import com.server.emcloud.domain.ProductType;
import com.server.emcloud.vo.ProductTypeAndTaskNum;
import com.server.emcloud.vo.ProductTypeExceptionNum;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/06/23/16:01
 * @Description:
 */
@Repository
public interface ProductTypeMapper {

    //查询全部的产品类型
    public List<ProductType> getAllProductType();
    //产品类型添加
    public int addProductType(ProductType productType);
    //产品类型信息更新
    public int updateProductType(ProductType productType);

    List<ProductTypeExceptionNum> getAllProductTypeExceptionNum();

    List<ProductTypeExceptionNum> getAllProductTypeExceptionNum2(String company_id);



    //根据产品类型id查询其任务数量
    public int getTaskNumByPid(int product_type_id);
    //根据产品类型id和公司id查询其任务数量
    public int getTaskNumByPidByCid(int product_type_id, int company_id);


}
