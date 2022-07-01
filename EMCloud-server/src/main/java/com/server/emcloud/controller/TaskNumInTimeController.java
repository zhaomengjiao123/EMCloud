package com.server.emcloud.controller;

import com.server.emcloud.service.TaskNumInTimeVOService;
import com.server.emcloud.vo.DiffProductTypeAndTaskNumInTimeVO;
import com.server.emcloud.vo.TaskNumInTimeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: mcj
 * @Date: 2022/7/1
 * @Description:
 */
@RestController
@RequestMapping("/TaskNumInTime")
public class TaskNumInTimeController {
    @Autowired
    private TaskNumInTimeVOService taskNumInTimeVOService;

    //查询一段时间内所有任务数量（时间是天）
    @GetMapping("/getAllTaskNumInTimeByDay")
    public List<TaskNumInTimeVO> getAllTaskNumInTimeByDay() {
        System.out.println("请求查询该时间段内每天的任务数量："+taskNumInTimeVOService.getAllTaskNumInTimeByDay());
        return taskNumInTimeVOService.getAllTaskNumInTimeByDay();
    }

    //查询一段时间内所有任务数量（时间是月）
    @GetMapping("/getAllTaskNumInTimeByMouth")
    public List<TaskNumInTimeVO> getAllTaskNumInTimeByMouth() {
        System.out.println("请求查询该时间段内每月的任务数量："+taskNumInTimeVOService.getAllTaskNumInTimeByMouth());
        return taskNumInTimeVOService.getAllTaskNumInTimeByMouth();
    }

    //查询一段时间内不同类型产品的任务数量（时间是天）
    @GetMapping("/getDiffProductTypeAndTaskNumInTimeByDay")
    public List<DiffProductTypeAndTaskNumInTimeVO> getDiffProductTypeAndTaskNumInTimeByDay() {
        System.out.println("请求查询不同类型产品在该时间段内每日的任务数量："+taskNumInTimeVOService.getDiffProductTypeAndTaskNumInTimeByDay());
        return taskNumInTimeVOService.getDiffProductTypeAndTaskNumInTimeByDay();
    }

    //查询一段时间内不同类型产品的任务数量（时间是月）
    @GetMapping("/getDiffProductTypeAndTaskNumInTimeByMouth")
    public List<DiffProductTypeAndTaskNumInTimeVO> getDiffProductTypeAndTaskNumInTimeByMouth() {
        System.out.println("请求查询不同类型产品在该时间段内每月的任务数量："+taskNumInTimeVOService.getDiffProductTypeAndTaskNumInTimeByMouth());
        return taskNumInTimeVOService.getDiffProductTypeAndTaskNumInTimeByMouth();
    }
}
