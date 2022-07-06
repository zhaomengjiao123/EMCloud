package com.server.emcloud.controller;

import com.server.emcloud.service.TaskNumInTimeVOService;
import com.server.emcloud.vo.DiffProductTypeAndTaskNumInTimeVO;
import com.server.emcloud.vo.TaskNumInTimeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    /**
    * @Description: 查询一段时间内所有任务数量（时间是天）
    * @Param:
     *     String startTime 开始日期
     *     String endTime 结束日期
    * @return:
    * @Author: mcj
    * @Date: 2022/7/1
    */
    @GetMapping("/getAllTaskNumInTimeByDay")
    public List<TaskNumInTimeVO> getAllTaskNumInTimeByDay(HttpServletRequest req) {
        String startTime = req.getParameter("startTime"); //开始日期
        String endTime = req.getParameter("endTime"); //结束日期
        System.out.println("请求查询该时间段内每天的任务数量："+taskNumInTimeVOService.getAllTaskNumInTimeByDay(startTime, endTime));
        return taskNumInTimeVOService.getAllTaskNumInTimeByDay(startTime, endTime);
    }

    /**
     * @Description: 查询一段时间内所有任务数量（时间是月）
     * @Param:
     *     String startTime 开始日期
     *     String endTime 结束日期
     * @return:
     * @Author: mcj
     * @Date: 2022/7/1
     */
    @GetMapping("/getAllTaskNumInTimeByMouth")
    public List<TaskNumInTimeVO> getAllTaskNumInTimeByMouth(HttpServletRequest req) {
        String startTime = req.getParameter("startTime"); //开始日期
        String endTime = req.getParameter("endTime"); //结束日期
        System.out.println("请求查询该时间段内每月的任务数量："+taskNumInTimeVOService.getAllTaskNumInTimeByMouth(startTime, endTime));
        return taskNumInTimeVOService.getAllTaskNumInTimeByMouth(startTime, endTime);
    }

    /**
     * @Description: 查询一段时间内不同类型产品的任务数量（时间是天）
     * @Param:
     *     String startTime 开始日期
     *     String endTime 结束日期
     * @return:
     * @Author: mcj
     * @Date: 2022/7/1
     */
    @GetMapping("/getDiffProductTypeAndTaskNumInTimeByDay")
    public List<DiffProductTypeAndTaskNumInTimeVO> getDiffProductTypeAndTaskNumInTimeByDay(HttpServletRequest req) {
        String startTime = req.getParameter("startTime"); //开始日期
        String endTime = req.getParameter("endTime"); //结束日期
        System.out.println("请求查询不同类型产品在该时间段内每日的任务数量："+taskNumInTimeVOService.getDiffProductTypeAndTaskNumInTimeByDay(startTime, endTime));


        return taskNumInTimeVOService.getDiffProductTypeAndTaskNumInTimeByDay(startTime, endTime);
    }

    /**
     * @Description: 查询一段时间内不同类型产品的任务数量（时间是月）
     * @Param:
     *     String startTime 开始日期
     *     String endTime 结束日期
     * @return:
     * @Author: mcj
     * @Date: 2022/7/1
     */
    @GetMapping("/getDiffProductTypeAndTaskNumInTimeByMouth")
    public List<DiffProductTypeAndTaskNumInTimeVO> getDiffProductTypeAndTaskNumInTimeByMouth(HttpServletRequest req) {
        String startTime = req.getParameter("startTime"); //开始日期
        String endTime = req.getParameter("endTime"); //结束日期
        System.out.println("请求查询不同类型产品在该时间段内每月的任务数量："+taskNumInTimeVOService.getDiffProductTypeAndTaskNumInTimeByMouth(startTime, endTime));
        return taskNumInTimeVOService.getDiffProductTypeAndTaskNumInTimeByMouth(startTime, endTime);
    }

    /**
     * @Description: 查询一段时间内某个产品的任务数量（时间是天）
     * @Param:
     * int productId 产品id
     * String startTime 开始日期
     * String endTime 结束日期
     * @return:
     * @Author: mcj
     * @Date: 2022/7/1
     */
    @GetMapping("/getProductTaskNumInTimeByPidByDay")
    public List<TaskNumInTimeVO> getProductTaskNumInTimeByPidByDay(HttpServletRequest req) {
        int productId = new Integer(req.getParameter("product_id")); //产品id
        String startTime = req.getParameter("startTime"); //开始日期
        String endTime = req.getParameter("endTime"); //结束日期
        System.out.println("请求查询某一产品在该时间段内每日的任务数量："
                +taskNumInTimeVOService.getProductTaskNumInTimeByPidByDay(productId, startTime, endTime));
        return taskNumInTimeVOService.getProductTaskNumInTimeByPidByDay(productId, startTime, endTime);
    }

    /**
     * @Description: 查询一段时间内某个产品的任务数量（时间是月）
     * @Param:
     * int productId 产品id
     * String startTime 开始日期
     * String endTime 结束日期
     * @return:
     * @Author: mcj
     * @Date: 2022/7/1
     */
    @GetMapping("/getProductTaskNumInTimeByPidByMouth")
    public List<TaskNumInTimeVO> getProductTaskNumInTimeByPidByMouth(HttpServletRequest req) {
        int productId = new Integer(req.getParameter("product_id")); //产品id
        String startTime = req.getParameter("startTime"); //开始日期
        String endTime = req.getParameter("endTime"); //结束日期
        System.out.println("请求查询某一产品在该时间段内每月的任务数量："
                +taskNumInTimeVOService.getProductTaskNumInTimeByPidByDay(productId, startTime, endTime));
        return taskNumInTimeVOService.getProductTaskNumInTimeByPidByMouth(productId, startTime, endTime);
    }
}
