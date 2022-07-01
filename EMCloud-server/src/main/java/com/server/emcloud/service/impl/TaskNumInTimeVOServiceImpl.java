package com.server.emcloud.service.impl;

import com.server.emcloud.dao.TaskNumInTimeVOMapper;
import com.server.emcloud.service.TaskNumInTimeVOService;
import com.server.emcloud.vo.DiffProductTypeAndTaskNumInTimeVO;
import com.server.emcloud.vo.TaskNumInTimeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Date: 2022/7/1
 * @Description:
 */
@Service
public class TaskNumInTimeVOServiceImpl implements TaskNumInTimeVOService {
    @Autowired
    private TaskNumInTimeVOMapper taskNumInTimeVOMapper;

    //查询一段时间内所有任务数量（时间是天）
    @Override
    public List<TaskNumInTimeVO> getAllTaskNumInTimeByDay() {
        return taskNumInTimeVOMapper.getAllTaskNumInTimeByDay();
    }

    //查询一段时间内所有任务数量（时间是月）
    @Override
    public List<TaskNumInTimeVO> getAllTaskNumInTimeByMouth() {
        return taskNumInTimeVOMapper.getAllTaskNumInTimeByMouth();
    }

    //查询一段时间内不同类型产品的任务数量（时间是天）
    @Override
    public List<DiffProductTypeAndTaskNumInTimeVO> getDiffProductTypeAndTaskNumInTimeByDay() {
        List<DiffProductTypeAndTaskNumInTimeVO> resultList = new ArrayList<>();

        //查询所有有任务的设备的产品类型id
        List<Integer> productTypeHasTask = taskNumInTimeVOMapper.getProductTypeHasTask();

        //循环得到的每个产品类型id
        for (Integer productTypeId : productTypeHasTask) {
            //构建返回结果
            DiffProductTypeAndTaskNumInTimeVO result = new DiffProductTypeAndTaskNumInTimeVO();
            //根据产品类型id获得产品类型名称
            result.setName(taskNumInTimeVOMapper.getProductNameById(productTypeId));
            //根据产品类型id获得该产品类型一段时间内的任务数量
            result.setData(taskNumInTimeVOMapper.getProductTypeTaskNumInTimeByIdByDay(productTypeId));
            //插入结果集
            resultList.add(result);
        }
        return resultList;
    }

    //查询一段时间内不同类型产品的任务数量（时间是月）
    @Override
    public List<DiffProductTypeAndTaskNumInTimeVO> getDiffProductTypeAndTaskNumInTimeByMouth() {
        List<DiffProductTypeAndTaskNumInTimeVO> resultList = new ArrayList<>();

        //查询所有有任务的设备的产品类型id和产品类型名称
        List<Integer> productTypeHasTask = taskNumInTimeVOMapper.getProductTypeHasTask();

        //循环得到的每个产品类型，通过id取得该类型的任务数量
        for (Integer productTypeId : productTypeHasTask) {
            //构建返回结果
            DiffProductTypeAndTaskNumInTimeVO result = new DiffProductTypeAndTaskNumInTimeVO();
            //根据产品类型id获得产品类型名称
            result.setName(taskNumInTimeVOMapper.getProductNameById(productTypeId));
            //根据产品类型id获得该产品类型一段时间内的任务数量
            result.setData(taskNumInTimeVOMapper.getProductTypeTaskNumInTimeByIdByMouth(productTypeId));
            //插入结果集
            resultList.add(result);
        }
        return resultList;
    }
}
