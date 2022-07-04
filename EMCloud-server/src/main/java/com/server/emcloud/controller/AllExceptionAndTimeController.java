package com.server.emcloud.controller;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 王俊博
 * @Description:
 */

import com.server.emcloud.domain.Company;
import com.server.emcloud.service.*;
import com.server.emcloud.utils.Consts;
import com.server.emcloud.vo.AllExceptionAndTimeVO;
import com.server.emcloud.vo.AllExceptionTypeAndTimeVO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/allExceptionAndTime")
public class AllExceptionAndTimeController {

    @Autowired
    private AllExceptionAndTimeVOService allExceptionAndTimeVOService;


    /**
     查询一段时间内所有异常数量（时间是天）
     author:王俊博
    */
    @GetMapping("getAllExceptionAndTimeByDay")
    public List<AllExceptionAndTimeVO> getAllExceptionAndTimeByDay(String startTime,String endTime){
        return allExceptionAndTimeVOService.getAllExceptionAndTimeByDay(startTime,endTime);
    }

    /**
     查询一段时间内所有异常数量（时间是月）
     author:王俊博
     */
    @GetMapping("getAllExceptionAndTimeByMonth")
    public List<AllExceptionAndTimeVO> getAllExceptionAndTimeByMonth(String startTime,String endTime){
        return allExceptionAndTimeVOService.getAllExceptionAndTimeByMonth(startTime,endTime);
    }

    /**
     查询一段时间内不同类型产品的异常数量（时间是天）
     author:王俊博
     */
    @GetMapping("getAllExceptionTypeAndTimeVOByDay")
    public List<AllExceptionTypeAndTimeVO> getAllExceptionTypeAndTimeVOByDay(String startTime,String endTime){
        return allExceptionAndTimeVOService.getAllExceptionTypeAndTimeVOByDay(startTime,endTime);
    }

    /**
     查询一段时间内不同类型产品的异常数量（时间是月）
     author:王俊博
     */
    @GetMapping("getAllExceptionTypeAndTimeVOByMonth")
    public List<AllExceptionTypeAndTimeVO> getAllExceptionTypeAndTimeVOByMonth(String startTime,String endTime){
        return allExceptionAndTimeVOService.getAllExceptionTypeAndTimeVOByMonth(startTime,endTime);
    }

    /**
     * 查询一段时间内某个产品异常数量（时间是天）
     * 王俊博
     */
    @GetMapping("getExceptionTypeAndTimeVOByDay")
    public List<AllExceptionAndTimeVO> getExceptionTypeAndTimeVOByDay(Integer product_id,String startTime,String endTime){
        return allExceptionAndTimeVOService.getExceptionTypeAndTimeVOByDay(product_id,startTime,endTime);
    }
    /**
     * 查询一段时间内某个产品异常数量（时间是月）
     * 王俊博
     */
    @GetMapping("getExceptionTypeAndTimeVOByMonth")
    public   List<AllExceptionAndTimeVO> getExceptionTypeAndTimeVOByMonth(Integer product_id,String startTime,String endTime){
        return allExceptionAndTimeVOService.getExceptionTypeAndTimeVOByMonth(product_id,startTime,endTime);
    }



}
