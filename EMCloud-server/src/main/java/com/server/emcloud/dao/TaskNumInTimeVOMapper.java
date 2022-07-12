package com.server.emcloud.dao;

import com.server.emcloud.vo.DiffProductTypeAndTaskNumInTimeVO;
import com.server.emcloud.vo.TaskNumInTimeVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: mcj
 * @Date: 2022/7/1
 * @Description: 某段时间内的任务数量
 */
@Repository
public interface TaskNumInTimeVOMapper {
    //查询一段时间内所有任务数量（时间是天）
    List<TaskNumInTimeVO> getAllTaskNumInTimeByDay();
    //查询一段时间内所有任务数量（时间是月）
    List<TaskNumInTimeVO> getAllTaskNumInTimeByMouth();
//    //查询一段时间内不同类型产品的任务数量（时间是天）
//    List<DiffProductTypeAndTaskNumInTimeVO> getDiffProductTypeAndTaskNumInTimeByDay();
//    //查询一段时间内不同类型产品的任务数量（时间是月）
//    List<DiffProductTypeAndTaskNumInTimeVO> getDiffProductTypeAndTaskNumInTimeByMouth();
    //查询一段时间内某个产品任务数量（时间是天）
    List<TaskNumInTimeVO> getProductTaskNumInTimeByPidByDay(int productId);
    //查询一段时间内某个产品任务数量（时间是月）
    List<TaskNumInTimeVO> getProductTaskNumInTimeByPidByMouth(int productId);

    //查询一段时间内某公司所有任务数量（时间是天）
    List<TaskNumInTimeVO> getAllTaskNumInTimeByDay2(int company_id);
    //查询一段时间某公司内所有任务数量（时间是月）
    List<TaskNumInTimeVO> getAllTaskNumInTimeByMouth2(int company_id);
    //查询一段时间某公司内某个产品任务数量（时间是天）
    List<TaskNumInTimeVO> getProductTaskNumInTimeByPidByDay2(int productId, int company_id);
    //查询一段时间内某公司某个产品任务数量（时间是月）
    List<TaskNumInTimeVO> getProductTaskNumInTimeByPidByMouth2(int productId, int company_id);


    //下面几个方法用于辅助上面的方法
    //查询所有有任务的产品类型id
    List<Integer> getProductTypeHasTask();
    //根据产品类型id获取产品类型名称
    String getProductNameById(int productTypeId);
    //查询指定产品类型的在一定时间内的任务数量任务数量(时间是天)
    List<TaskNumInTimeVO> getProductTypeTaskNumInTimeByIdByDay(int productTypeId);
    //查询指定产品类型的在一定时间内的任务数量任务数量(时间是月)
    List<TaskNumInTimeVO> getProductTypeTaskNumInTimeByIdByMouth(int productTypeId);
    //查询指定产品类型的在一定时间内的任务数量任务数量(时间是天)
    List<TaskNumInTimeVO> getProductTypeTaskNumInTimeByIdByDay2(int productTypeId, int company_id);
    //查询指定产品类型的在一定时间内的任务数量任务数量(时间是月)
    List<TaskNumInTimeVO> getProductTypeTaskNumInTimeByIdByMouth2(int productTypeId, int company_id);
}
