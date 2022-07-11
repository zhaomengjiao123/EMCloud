package com.server.emcloud.service;

import com.server.emcloud.vo.AllExceptionAndTimeVO;
import com.server.emcloud.vo.AllExceptionTypeAndTimeVO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 王俊博

 */
public interface AllExceptionAndTimeVOService {

  List<AllExceptionAndTimeVO> getAllExceptionAndTimeByDay(String startTime, String endTime);

  List<AllExceptionAndTimeVO> getAllExceptionAndTimeByMonth(String startTime, String endTime);

  List<AllExceptionTypeAndTimeVO> getAllExceptionTypeAndTimeVOByDay(String startTime, String endTime);

  List<AllExceptionTypeAndTimeVO> getAllExceptionTypeAndTimeVOByMonth(String startTime, String endTime);


  List<AllExceptionAndTimeVO> getExceptionTypeAndTimeVOByMonth(Integer product_id, String startTime, String endTime);

  List<AllExceptionAndTimeVO> getExceptionTypeAndTimeVOByDay(Integer product_id, String startTime, String endTime);


  List<AllExceptionAndTimeVO> getAllExceptionAndTimeByDay2(String startTime, String endTime,String company_id);

  List<AllExceptionAndTimeVO> getAllExceptionAndTimeByMonth2(String startTime, String endTime,String company_id);

  List<AllExceptionTypeAndTimeVO> getAllExceptionTypeAndTimeVOByDay2(String startTime, String endTime,String company_id);

  List<AllExceptionTypeAndTimeVO> getAllExceptionTypeAndTimeVOByMonth2(String startTime, String endTime,String company_id);


  List<AllExceptionAndTimeVO> getExceptionTypeAndTimeVOByMonth2(Integer product_id, String startTime, String endTime,String company_id);

  List<AllExceptionAndTimeVO> getExceptionTypeAndTimeVOByDay2(Integer product_id, String startTime, String endTime,String company_id);
}
