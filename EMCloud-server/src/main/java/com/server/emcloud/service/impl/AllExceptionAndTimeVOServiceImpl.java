package com.server.emcloud.service.impl;

import com.server.emcloud.dao.AllExceptionAndTimeVOMapper;
import com.server.emcloud.dao.ProductMapper;
import com.server.emcloud.domain.Product;
import com.server.emcloud.service.AllExceptionAndTimeVOService;
import com.server.emcloud.vo.AllExceptionAndTimeVO;
import com.server.emcloud.vo.AllExceptionTypeAndTimeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
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
    public List<AllExceptionAndTimeVO> getAllExceptionAndTimeByDay(String startTime, String endTime) {
        List<AllExceptionAndTimeVO> allExceptionAndTimeByDay = allExceptionAndTimeVOMapper.getAllExceptionAndTimeByDay();
        Iterator<AllExceptionAndTimeVO> iterator = allExceptionAndTimeByDay.iterator();
        while (iterator.hasNext()) {
            AllExceptionAndTimeVO next = iterator.next();
            if (next.getTime().compareTo( endTime)>0 || next.getTime().compareTo(startTime)<0) {
                iterator.remove();
            }
        }
        return allExceptionAndTimeByDay;
    }

    @Override
    public List<AllExceptionAndTimeVO> getAllExceptionAndTimeByMonth(String startTime, String endTime) {
        List<AllExceptionAndTimeVO> allExceptionAndTimeByMonth = allExceptionAndTimeVOMapper.getAllExceptionAndTimeByMonth();
        Iterator<AllExceptionAndTimeVO> iterator = allExceptionAndTimeByMonth.iterator();
        while (iterator.hasNext()) {
            AllExceptionAndTimeVO next = iterator.next();
            if (next.getTime().compareTo( endTime)>0 || next.getTime().compareTo(startTime)<0) {
                iterator.remove();
            }
        }
        return allExceptionAndTimeByMonth;
    }

    @Override
    public List<AllExceptionTypeAndTimeVO> getAllExceptionTypeAndTimeVOByDay(String startTime, String endTime) {
        List<AllExceptionTypeAndTimeVO> result = new ArrayList<>();
        List<Product> lists = productMapper.getAllProduct();
        for (Product product: lists) {
            AllExceptionTypeAndTimeVO allExceptionTypeAndTimeVO = new AllExceptionTypeAndTimeVO();
            allExceptionTypeAndTimeVO.setName(product.getProduct_name());
            List<AllExceptionAndTimeVO> exceptionOfProductByDay = allExceptionAndTimeVOMapper.getExceptionOfProductByDay(product.getProduct_id());
            Iterator<AllExceptionAndTimeVO> iterator = exceptionOfProductByDay.iterator();
            while (iterator.hasNext()) {
                AllExceptionAndTimeVO next = iterator.next();
                if (next.getTime().compareTo( endTime)>0 || next.getTime().compareTo(startTime)<0) {
                    iterator.remove();
                }
            }
            allExceptionTypeAndTimeVO.setList(exceptionOfProductByDay);
            result.add(allExceptionTypeAndTimeVO);
        }

        return result;

    }

    @Override
    public List<AllExceptionTypeAndTimeVO> getAllExceptionTypeAndTimeVOByMonth(String startTime, String endTime) {
        List<AllExceptionTypeAndTimeVO> result = new ArrayList<>();
        List<Product> lists = productMapper.getAllProduct();
        for (Product product: lists) {
            AllExceptionTypeAndTimeVO allExceptionTypeAndTimeVO = new AllExceptionTypeAndTimeVO();
            allExceptionTypeAndTimeVO.setName(product.getProduct_name());
            List<AllExceptionAndTimeVO> exceptionOfProductByMonth = allExceptionAndTimeVOMapper.getExceptionOfProductByMonth(product.getProduct_id());
            Iterator<AllExceptionAndTimeVO> iterator = exceptionOfProductByMonth.iterator();
            while (iterator.hasNext()) {
                AllExceptionAndTimeVO next = iterator.next();
                if (next.getTime().compareTo( endTime)>0 || next.getTime().compareTo(startTime)<0) {
                    iterator.remove();
                }
            }
            allExceptionTypeAndTimeVO.setList(exceptionOfProductByMonth);
            result.add(allExceptionTypeAndTimeVO);
        }


        return result;
    }

    @Override
    public List<AllExceptionAndTimeVO> getExceptionTypeAndTimeVOByMonth(Integer product_id, String startTime, String endTime) {
        List<AllExceptionAndTimeVO> exceptionOfProductByMonth = allExceptionAndTimeVOMapper.getExceptionOfProductByMonth(product_id);
        Iterator<AllExceptionAndTimeVO> iterator = exceptionOfProductByMonth.iterator();
        while (iterator.hasNext()) {
            AllExceptionAndTimeVO next = iterator.next();
            if (next.getTime().compareTo( endTime)>0 || next.getTime().compareTo(startTime)<0) {
                iterator.remove();
            }
        }
        return exceptionOfProductByMonth;
    }

    @Override
    public  List<AllExceptionAndTimeVO> getExceptionTypeAndTimeVOByDay(Integer product_id, String startTime, String endTime) {

        List<AllExceptionAndTimeVO> exceptionOfProductByDay = allExceptionAndTimeVOMapper.getExceptionOfProductByDay(product_id);
        Iterator<AllExceptionAndTimeVO> iterator = exceptionOfProductByDay.iterator();
        while (iterator.hasNext()) {
            AllExceptionAndTimeVO next = iterator.next();
            if (next.getTime().compareTo( endTime)>0 || next.getTime().compareTo(startTime)<0) {
                iterator.remove();
            }
        }
        return exceptionOfProductByDay;
    }


    /**
     * 接下来是新加的功能，新增参数company_id
     * @param startTime
     * @param endTime
     * @param company_id
     * @return
     */
    @Override
    public List<AllExceptionAndTimeVO> getAllExceptionAndTimeByDay2(String startTime, String endTime,String company_id) {
        List<AllExceptionAndTimeVO> allExceptionAndTimeByDay = allExceptionAndTimeVOMapper.getAllExceptionAndTimeByDay2(company_id);
        Iterator<AllExceptionAndTimeVO> iterator = allExceptionAndTimeByDay.iterator();
        while (iterator.hasNext()) {
            AllExceptionAndTimeVO next = iterator.next();
            if (next.getTime().compareTo( endTime)>0 || next.getTime().compareTo(startTime)<0) {
                iterator.remove();
            }
        }
        return allExceptionAndTimeByDay;
    }

    @Override
    public List<AllExceptionAndTimeVO> getAllExceptionAndTimeByMonth2(String startTime, String endTime,String company_id) {
        List<AllExceptionAndTimeVO> allExceptionAndTimeByMonth = allExceptionAndTimeVOMapper.getAllExceptionAndTimeByMonth2(company_id);
        Iterator<AllExceptionAndTimeVO> iterator = allExceptionAndTimeByMonth.iterator();
        while (iterator.hasNext()) {
            AllExceptionAndTimeVO next = iterator.next();
            if (next.getTime().compareTo( endTime)>0 || next.getTime().compareTo(startTime)<0) {
                iterator.remove();
            }
        }
        return allExceptionAndTimeByMonth;
    }

    @Override
    public List<AllExceptionTypeAndTimeVO> getAllExceptionTypeAndTimeVOByDay2(String startTime, String endTime,String company_id) {
        List<AllExceptionTypeAndTimeVO> result = new ArrayList<>();
        List<Product> lists = productMapper.getAllProduct();
        for (Product product: lists) {
            AllExceptionTypeAndTimeVO allExceptionTypeAndTimeVO = new AllExceptionTypeAndTimeVO();
            allExceptionTypeAndTimeVO.setName(product.getProduct_name());
            List<AllExceptionAndTimeVO> exceptionOfProductByDay = allExceptionAndTimeVOMapper.getExceptionOfProductByDay2(product.getProduct_id(),company_id);
            Iterator<AllExceptionAndTimeVO> iterator = exceptionOfProductByDay.iterator();
            while (iterator.hasNext()) {
                AllExceptionAndTimeVO next = iterator.next();
                if (next.getTime().compareTo( endTime)>0 || next.getTime().compareTo(startTime)<0) {
                    iterator.remove();
                }
            }
            allExceptionTypeAndTimeVO.setList(exceptionOfProductByDay);
            result.add(allExceptionTypeAndTimeVO);
        }

        return result;

    }

    @Override
    public List<AllExceptionTypeAndTimeVO> getAllExceptionTypeAndTimeVOByMonth2(String startTime, String endTime,String company_id) {
        List<AllExceptionTypeAndTimeVO> result = new ArrayList<>();
        List<Product> lists = productMapper.getAllProduct();
        for (Product product: lists) {
            AllExceptionTypeAndTimeVO allExceptionTypeAndTimeVO = new AllExceptionTypeAndTimeVO();
            allExceptionTypeAndTimeVO.setName(product.getProduct_name());
            List<AllExceptionAndTimeVO> exceptionOfProductByMonth = allExceptionAndTimeVOMapper.getExceptionOfProductByMonth2(product.getProduct_id(),company_id);
            Iterator<AllExceptionAndTimeVO> iterator = exceptionOfProductByMonth.iterator();
            while (iterator.hasNext()) {
                AllExceptionAndTimeVO next = iterator.next();
                if (next.getTime().compareTo( endTime)>0 || next.getTime().compareTo(startTime)<0) {
                    iterator.remove();
                }
            }
            allExceptionTypeAndTimeVO.setList(exceptionOfProductByMonth);
            result.add(allExceptionTypeAndTimeVO);
        }


        return result;
    }

    @Override
    public List<AllExceptionAndTimeVO> getExceptionTypeAndTimeVOByMonth2(Integer product_id, String startTime, String endTime,String company_id) {
        List<AllExceptionAndTimeVO> exceptionOfProductByMonth = allExceptionAndTimeVOMapper.getExceptionOfProductByMonth2(product_id,company_id);
        Iterator<AllExceptionAndTimeVO> iterator = exceptionOfProductByMonth.iterator();
        while (iterator.hasNext()) {
            AllExceptionAndTimeVO next = iterator.next();
            if (next.getTime().compareTo( endTime)>0 || next.getTime().compareTo(startTime)<0) {
                iterator.remove();
            }
        }
        return exceptionOfProductByMonth;
    }

    @Override
    public  List<AllExceptionAndTimeVO> getExceptionTypeAndTimeVOByDay2(Integer product_id, String startTime, String endTime,String company_id) {

        List<AllExceptionAndTimeVO> exceptionOfProductByDay = allExceptionAndTimeVOMapper.getExceptionOfProductByDay2(product_id,company_id);
        Iterator<AllExceptionAndTimeVO> iterator = exceptionOfProductByDay.iterator();
        while (iterator.hasNext()) {
            AllExceptionAndTimeVO next = iterator.next();
            if (next.getTime().compareTo( endTime)>0 || next.getTime().compareTo(startTime)<0) {
                iterator.remove();
            }
        }
        return exceptionOfProductByDay;
    }
}
