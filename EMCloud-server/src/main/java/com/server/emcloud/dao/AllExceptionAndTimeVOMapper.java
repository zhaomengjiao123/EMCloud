package com.server.emcloud.dao;

import com.server.emcloud.domain.Company;
import com.server.emcloud.vo.AllExceptionAndTimeVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 王俊博
 * @Description:
 */
@Repository
public interface AllExceptionAndTimeVOMapper {

    List<AllExceptionAndTimeVO>getAllExceptionAndTimeByDay();

    List<AllExceptionAndTimeVO>getAllExceptionAndTimeByDay2(String company_id);


    List<AllExceptionAndTimeVO> getAllExceptionAndTimeByMonth();

    List<AllExceptionAndTimeVO> getAllExceptionAndTimeByMonth2(String company_id);


    List<AllExceptionAndTimeVO>getYUJINGByDay();

    List<AllExceptionAndTimeVO> getBAOJINGByDay();

    List<AllExceptionAndTimeVO> getJINJIJINGGAOByDay();

    List<AllExceptionAndTimeVO>getYUJINGByMonth();

    List<AllExceptionAndTimeVO> getBAOJINGByMonth();

    List<AllExceptionAndTimeVO> getJINJIJINGGAOByMonth();

    List<AllExceptionAndTimeVO> getExceptionOfProductByDay(Integer product_id);
    List<AllExceptionAndTimeVO> getExceptionOfProductByDay2(Integer product_id,String company_id);


    List<AllExceptionAndTimeVO> getExceptionOfProductByMonth(Integer product_id);

    List<AllExceptionAndTimeVO> getExceptionOfProductByMonth2(Integer product_id,String company_id);


}
