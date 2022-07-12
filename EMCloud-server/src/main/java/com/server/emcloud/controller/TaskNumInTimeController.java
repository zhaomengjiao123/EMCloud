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
     * @Description: 查询一段时间内某公司所有任务数量（时间是天）
     * @Param:
     *     String startTime 开始日期
     *     String endTime 结束日期
     *     int company_id 所属公司id
     * @return:
     * @Author: mcj
     * @Date: 2022/7/12
     */
    @GetMapping("/getAllTaskNumInTimeByDay2")
    public List<TaskNumInTimeVO> getAllTaskNumInTimeByDay2(HttpServletRequest req) {
        String startTime = req.getParameter("startTime"); //开始日期
        String endTime = req.getParameter("endTime"); //结束日期
        int company_id = new Integer(req.getParameter("company_id")); //所属公司id
        System.out.println("请求查询该时间段内每天的任务数量："+taskNumInTimeVOService.getAllTaskNumInTimeByDay2(startTime, endTime, company_id));
        return taskNumInTimeVOService.getAllTaskNumInTimeByDay2(startTime, endTime, company_id);
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
     * @Description: 查询一段时间内某公司所有任务数量（时间是月）
     * @Param:
     *     String startTime 开始日期
     *     String endTime 结束日期
     *     int company_id 所属公司id
     * @return:
     * @Author: mcj
     * @Date: 2022/7/12
     */
    @GetMapping("/getAllTaskNumInTimeByMouth2")
    public List<TaskNumInTimeVO> getAllTaskNumInTimeByMouth2(HttpServletRequest req) {
        String startTime = req.getParameter("startTime"); //开始日期
        String endTime = req.getParameter("endTime"); //结束日期
        int company_id = new Integer(req.getParameter("company_id")); //所属公司id
        System.out.println("请求查询该时间段内每月的任务数量："+taskNumInTimeVOService.getAllTaskNumInTimeByMouth2(startTime, endTime, company_id));
        return taskNumInTimeVOService.getAllTaskNumInTimeByMouth2(startTime, endTime, company_id);
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
     * @Description: 查询一段时间内某公司不同类型产品的任务数量（时间是天）
     * @Param:
     *     String startTime 开始日期
     *     String endTime 结束日期
     *     int company_id 所属公司id
     * @return:
     * @Author: mcj
     * @Date: 2022/7/12
     */
    @GetMapping("/getDiffProductTypeAndTaskNumInTimeByDay2")
    public List<DiffProductTypeAndTaskNumInTimeVO> getDiffProductTypeAndTaskNumInTimeByDay2(HttpServletRequest req) {
        String startTime = req.getParameter("startTime"); //开始日期
        String endTime = req.getParameter("endTime"); //结束日期
        int company_id = new Integer(req.getParameter("company_id")); //所属公司id
        System.out.println("请求查询不同类型产品在该时间段内每日的任务数量："+taskNumInTimeVOService.getDiffProductTypeAndTaskNumInTimeByDay2(startTime, endTime, company_id));


        return taskNumInTimeVOService.getDiffProductTypeAndTaskNumInTimeByDay2(startTime, endTime, company_id);
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
     * @Description: 查询一段时间内某公司不同类型产品的任务数量（时间是月）
     * @Param:
     *     String startTime 开始日期
     *     String endTime 结束日期
     *     int company_id 所属公司id
     * @return:
     * @Author: mcj
     * @Date: 2022/7/12
     */
    @GetMapping("/getDiffProductTypeAndTaskNumInTimeByMouth2")
    public List<DiffProductTypeAndTaskNumInTimeVO> getDiffProductTypeAndTaskNumInTimeByMouth2(HttpServletRequest req) {
        String startTime = req.getParameter("startTime"); //开始日期
        String endTime = req.getParameter("endTime"); //结束日期
        int company_id = new Integer(req.getParameter("company_id")); //所属公司id
        System.out.println("请求查询不同类型产品在该时间段内每月的任务数量："+taskNumInTimeVOService.getDiffProductTypeAndTaskNumInTimeByMouth2(startTime, endTime, company_id));
        return taskNumInTimeVOService.getDiffProductTypeAndTaskNumInTimeByMouth2(startTime, endTime, company_id);
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
     * @Description: 查询一段时间内某公司某个产品的任务数量（时间是天）
     * @Param:
     * int productId 产品id
     * int company_id 所属公司id
     * String startTime 开始日期
     * String endTime 结束日期
     * @return:
     * @Author: mcj
     * @Date: 2022/7/12
     */
    @GetMapping("/getProductTaskNumInTimeByPidByDay2")
    public List<TaskNumInTimeVO> getProductTaskNumInTimeByPidByDay2(HttpServletRequest req) {
        int productId = new Integer(req.getParameter("product_id")); //产品id
        int company_id = new Integer(req.getParameter("company_id")); //所属公司id
        String startTime = req.getParameter("startTime"); //开始日期
        String endTime = req.getParameter("endTime"); //结束日期
        System.out.println("请求查询某一产品在该时间段内每日的任务数量："
                +taskNumInTimeVOService.getProductTaskNumInTimeByPidByDay2(productId, startTime, endTime, company_id));
        return taskNumInTimeVOService.getProductTaskNumInTimeByPidByDay2(productId, startTime, endTime, company_id);
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
                +taskNumInTimeVOService.getProductTaskNumInTimeByPidByMouth(productId, startTime, endTime));
        return taskNumInTimeVOService.getProductTaskNumInTimeByPidByMouth(productId, startTime, endTime);
    }

    /**
     * @Description: 查询一段时间内某公司某个产品的任务数量（时间是月）
     * @Param:
     * int productId 产品id
     * int company_id 所属公司id
     * String startTime 开始日期
     * String endTime 结束日期
     * @return:
     * @Author: mcj
     * @Date: 2022/7/12
     */
    @GetMapping("/getProductTaskNumInTimeByPidByMouth2")
    public List<TaskNumInTimeVO> getProductTaskNumInTimeByPidByMouth2(HttpServletRequest req) {
        int productId = new Integer(req.getParameter("product_id")); //产品id
        int company_id = new Integer(req.getParameter("company_id")); //所属公司id
        String startTime = req.getParameter("startTime"); //开始日期
        String endTime = req.getParameter("endTime"); //结束日期
        System.out.println("请求查询某一产品在该时间段内每月的任务数量："
                +taskNumInTimeVOService.getProductTaskNumInTimeByPidByMouth2(productId, startTime, endTime, company_id));
        return taskNumInTimeVOService.getProductTaskNumInTimeByPidByMouth2(productId, startTime, endTime, company_id);
    }
}
