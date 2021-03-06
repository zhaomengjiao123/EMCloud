package com.server.emcloud.service;

import com.server.emcloud.vo.DiffProductTypeAndTaskNumInTimeVO;
import com.server.emcloud.vo.TaskNumInTimeVO;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: mcj
 * @Date: 2022/7/1
 * @Description:
 */
public interface TaskNumInTimeVOService {
    //查询一段时间内所有任务数量（时间是天）
    List<TaskNumInTimeVO> getAllTaskNumInTimeByDay(String startTime, String endTime);
    //查询一段时间内所有任务数量（时间是月）
    List<TaskNumInTimeVO> getAllTaskNumInTimeByMouth(String startTime, String endTime);
    //查询一段时间内不同类型产品的任务数量（时间是天）
    List<DiffProductTypeAndTaskNumInTimeVO> getDiffProductTypeAndTaskNumInTimeByDay(String startTime, String endTime);
    //查询一段时间内不同类型产品的任务数量（时间是月）
    List<DiffProductTypeAndTaskNumInTimeVO> getDiffProductTypeAndTaskNumInTimeByMouth(String startTime, String endTime);
    //查询一段时间内某个产品任务数量（时间是天）
    List<TaskNumInTimeVO> getProductTaskNumInTimeByPidByDay(int productId, String startTime, String endTime);
    //查询一段时间内某个产品任务数量（时间是月）
    List<TaskNumInTimeVO> getProductTaskNumInTimeByPidByMouth(int productId,String startTime, String endTime);

    //查询一段时间某公司内所有任务数量（时间是天）
    List<TaskNumInTimeVO> getAllTaskNumInTimeByDay2(String startTime, String endTime, int company_id);
    //查询一段时间内某公司所有任务数量（时间是月）
    List<TaskNumInTimeVO> getAllTaskNumInTimeByMouth2(String startTime, String endTime, int company_id);
    //查询一段时间内某公司不同类型产品的任务数量（时间是天）
    List<DiffProductTypeAndTaskNumInTimeVO> getDiffProductTypeAndTaskNumInTimeByDay2(String startTime, String endTime, int company_id);
    //查询一段时间内某公司不同类型产品的任务数量（时间是月）
    List<DiffProductTypeAndTaskNumInTimeVO> getDiffProductTypeAndTaskNumInTimeByMouth2(String startTime, String endTime, int company_id);
    //查询一段时间内某公司某个产品任务数量（时间是天）
    List<TaskNumInTimeVO> getProductTaskNumInTimeByPidByDay2(int productId, String startTime, String endTime, int company_id);
    //查询一段时间内某公司某个产品任务数量（时间是月）
    List<TaskNumInTimeVO> getProductTaskNumInTimeByPidByMouth2(int productId,String startTime, String endTime, int company_id);
}
