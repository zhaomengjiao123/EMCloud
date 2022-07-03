package com.server.emcloud.service.impl;

import com.server.emcloud.dao.AllExceptionAndTimeVOMapper;
import com.server.emcloud.dao.CompanyMapper;
import com.server.emcloud.dao.ProductMapper;
import com.server.emcloud.domain.Company;
import com.server.emcloud.domain.Product;
import com.server.emcloud.service.AllExceptionAndTimeVOService;
import com.server.emcloud.service.CompanyService;
import com.server.emcloud.vo.AllExceptionAndTimeVO;
import com.server.emcloud.vo.AllExceptionTypeAndTimeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 王俊博

 */
@Service
public class AllExceptionAndTimeVOServiceImpl implements AllExceptionAndTimeVOService {

    @Autowired
    AllExceptionAndTimeVOMapper allExceptionAndTimeVOMapper;

    @Autowired
    ProductMapper productMapper;

    @Override
    public List<AllExceptionAndTimeVO> getAllExceptionAndTimeByDay() {
        return allExceptionAndTimeVOMapper.getAllExceptionAndTimeByDay();
    }

    @Override
    public List<AllExceptionAndTimeVO> getAllExceptionAndTimeByMonth() {
        return allExceptionAndTimeVOMapper.getAllExceptionAndTimeByMonth();
    }

    @Override
    public List<AllExceptionTypeAndTimeVO> getAllExceptionTypeAndTimeVOByDay() {
        List<AllExceptionTypeAndTimeVO> result = new ArrayList<>();
        List<Product> lists = productMapper.getAllProduct();
        for (Product product: lists) {
            AllExceptionTypeAndTimeVO allExceptionTypeAndTimeVO = new AllExceptionTypeAndTimeVO();
            allExceptionTypeAndTimeVO.setName(product.getProduct_name());
            allExceptionTypeAndTimeVO.setList(allExceptionAndTimeVOMapper.getExceptionOfProductByDay(product.getProduct_id()));
            result.add(allExceptionTypeAndTimeVO);
        }


        return result;

    }

    @Override
    public List<AllExceptionTypeAndTimeVO> getAllExceptionTypeAndTimeVOByMonth() {
        List<AllExceptionTypeAndTimeVO> result = new ArrayList<>();
        List<Product> lists = productMapper.getAllProduct();
        for (Product product: lists) {
            AllExceptionTypeAndTimeVO allExceptionTypeAndTimeVO = new AllExceptionTypeAndTimeVO();
            allExceptionTypeAndTimeVO.setName(product.getProduct_name());
            allExceptionTypeAndTimeVO.setList(allExceptionAndTimeVOMapper.getExceptionOfProductByMonth(product.getProduct_id()));
            result.add(allExceptionTypeAndTimeVO);
        }


        return result;
    }

    @Override
    public List<AllExceptionAndTimeVO> getExceptionTypeAndTimeVOByMonth(Integer product_id) {
        return allExceptionAndTimeVOMapper.getExceptionOfProductByMonth(product_id) ;
    }

    @Override
    public  List<AllExceptionAndTimeVO> getExceptionTypeAndTimeVOByDay(Integer product_id) {
        return allExceptionAndTimeVOMapper.getExceptionOfProductByDay(product_id);
    }
}
