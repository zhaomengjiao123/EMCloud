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
    public List<AllExceptionAndTimeVO> getAllExceptionAndTimeByDay(){
        return allExceptionAndTimeVOService.getAllExceptionAndTimeByDay();
    }

    /**
     查询一段时间内所有异常数量（时间是月）
     author:王俊博
     */
    @GetMapping("getAllExceptionAndTimeByMonth")
    public List<AllExceptionAndTimeVO> getAllExceptionAndTimeByMonth(){
        return allExceptionAndTimeVOService.getAllExceptionAndTimeByMonth();
    }

    /**
     查询一段时间内不同类型产品的异常数量（时间是天）
     author:王俊博
     */
    @GetMapping("getAllExceptionTypeAndTimeVOByDay")
    public List<AllExceptionTypeAndTimeVO> getAllExceptionTypeAndTimeVOByDay(){
        return allExceptionAndTimeVOService.getAllExceptionTypeAndTimeVOByDay();
    }

    /**
     查询一段时间内不同类型产品的异常数量（时间是月）
     author:王俊博
     */
    @GetMapping("getAllExceptionTypeAndTimeVOByMonth")
    public List<AllExceptionTypeAndTimeVO> getAllExceptionTypeAndTimeVOByMonth(){
        return allExceptionAndTimeVOService.getAllExceptionTypeAndTimeVOByMonth();
    }




}
