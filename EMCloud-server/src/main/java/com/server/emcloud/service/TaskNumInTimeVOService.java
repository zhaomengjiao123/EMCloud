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
    List<TaskNumInTimeVO> getAllTaskNumInTimeByDay();
    //查询一段时间内所有任务数量（时间是月）
    List<TaskNumInTimeVO> getAllTaskNumInTimeByMouth();
    //查询一段时间内不同类型产品的任务数量（时间是天）
    List<DiffProductTypeAndTaskNumInTimeVO> getDiffProductTypeAndTaskNumInTimeByDay();
    //查询一段时间内不同类型产品的任务数量（时间是月）
    List<DiffProductTypeAndTaskNumInTimeVO> getDiffProductTypeAndTaskNumInTimeByMouth();
    //查询一段时间内某个产品任务数量（时间是天）
    List<TaskNumInTimeVO> getProductTaskNumInTimeByPidByDay(int productId);
    //查询一段时间内某个产品任务数量（时间是月）
    List<TaskNumInTimeVO> getProductTaskNumInTimeByPidByMouth(int productId);

}
