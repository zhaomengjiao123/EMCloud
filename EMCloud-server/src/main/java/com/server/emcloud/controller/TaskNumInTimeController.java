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
    * @return:
    * @Author: mcj
    * @Date: 2022/7/1
    */
    @GetMapping("/getAllTaskNumInTimeByDay")
    public List<TaskNumInTimeVO> getAllTaskNumInTimeByDay(HttpServletRequest req) {
        System.out.println("请求查询该时间段内每天的任务数量："+taskNumInTimeVOService.getAllTaskNumInTimeByDay());
        return taskNumInTimeVOService.getAllTaskNumInTimeByDay();
    }

    /**
     * @Description: 查询一段时间内所有任务数量（时间是月）
     * @Param:
     * @return:
     * @Author: mcj
     * @Date: 2022/7/1
     */
    @GetMapping("/getAllTaskNumInTimeByMouth")
    public List<TaskNumInTimeVO> getAllTaskNumInTimeByMouth(HttpServletRequest req) {
        System.out.println("请求查询该时间段内每月的任务数量："+taskNumInTimeVOService.getAllTaskNumInTimeByMouth());
        return taskNumInTimeVOService.getAllTaskNumInTimeByMouth();
    }

    /**
     * @Description: 查询一段时间内不同类型产品的任务数量（时间是天）
     * @Param:
     * @return:
     * @Author: mcj
     * @Date: 2022/7/1
     */
    @GetMapping("/getDiffProductTypeAndTaskNumInTimeByDay")
    public List<DiffProductTypeAndTaskNumInTimeVO> getDiffProductTypeAndTaskNumInTimeByDay(HttpServletRequest req) {
        System.out.println("请求查询不同类型产品在该时间段内每日的任务数量："+taskNumInTimeVOService.getDiffProductTypeAndTaskNumInTimeByDay());
        return taskNumInTimeVOService.getDiffProductTypeAndTaskNumInTimeByDay();
    }

    /**
     * @Description: 查询一段时间内不同类型产品的任务数量（时间是月）
     * @Param:
     * @return:
     * @Author: mcj
     * @Date: 2022/7/1
     */
    @GetMapping("/getDiffProductTypeAndTaskNumInTimeByMouth")
    public List<DiffProductTypeAndTaskNumInTimeVO> getDiffProductTypeAndTaskNumInTimeByMouth(HttpServletRequest req) {
        System.out.println("请求查询不同类型产品在该时间段内每月的任务数量："+taskNumInTimeVOService.getDiffProductTypeAndTaskNumInTimeByMouth());
        return taskNumInTimeVOService.getDiffProductTypeAndTaskNumInTimeByMouth();
    }

    /**
     * @Description: 查询一段时间内某个产品的任务数量（时间是天）
     * @Param: int productId
     * @return:
     * @Author: mcj
     * @Date: 2022/7/1
     */
    @GetMapping("/getProductTaskNumInTimeByPidByDay")
    public List<TaskNumInTimeVO> getProductTaskNumInTimeByPidByDay(HttpServletRequest req) {
        int productId = new Integer(req.getParameter("product_id"));
        System.out.println("请求查询某一产品在该时间段内每日的任务数量："
                +taskNumInTimeVOService.getProductTaskNumInTimeByPidByDay(productId));
        return taskNumInTimeVOService.getProductTaskNumInTimeByPidByDay(productId);
    }

    /**
     * @Description: 查询一段时间内某个产品的任务数量（时间是月）
     * @Param: int productId
     * @return:
     * @Author: mcj
     * @Date: 2022/7/1
     */
    @GetMapping("/getProductTaskNumInTimeByPidByMouth")
    public List<TaskNumInTimeVO> getProductTaskNumInTimeByPidByMouth(HttpServletRequest req) {
        int productId = new Integer(req.getParameter("product_id"));
        System.out.println("请求查询某一产品在该时间段内每月的任务数量："
                +taskNumInTimeVOService.getProductTaskNumInTimeByPidByDay(productId));
        return taskNumInTimeVOService.getProductTaskNumInTimeByPidByMouth(productId);
    }
}
