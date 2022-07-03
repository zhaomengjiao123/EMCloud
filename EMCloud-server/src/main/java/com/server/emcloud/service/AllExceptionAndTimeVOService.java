package com.server.emcloud.service;

import com.server.emcloud.domain.Company;
import com.server.emcloud.vo.AllExceptionAndTimeVO;
import com.server.emcloud.vo.AllExceptionTypeAndTimeVO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 王俊博

 */
public interface AllExceptionAndTimeVOService {

  List<AllExceptionAndTimeVO> getAllExceptionAndTimeByDay();

  List<AllExceptionAndTimeVO> getAllExceptionAndTimeByMonth();

  List<AllExceptionTypeAndTimeVO> getAllExceptionTypeAndTimeVOByDay();

  List<AllExceptionTypeAndTimeVO> getAllExceptionTypeAndTimeVOByMonth();


  List<AllExceptionAndTimeVO> getExceptionTypeAndTimeVOByMonth(Integer product_id);

  List<AllExceptionAndTimeVO> getExceptionTypeAndTimeVOByDay(Integer product_id);
}
