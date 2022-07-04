package com.server.emcloud.service.impl;

import com.server.emcloud.dao.TaskNumInTimeVOMapper;
import com.server.emcloud.service.TaskNumInTimeVOService;
import com.server.emcloud.vo.DiffProductTypeAndTaskNumInTimeVO;
import com.server.emcloud.vo.TaskNumInTimeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
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
    public List<TaskNumInTimeVO> getAllTaskNumInTimeByDay(String startTime, String endTime) {
        List<TaskNumInTimeVO> result = taskNumInTimeVOMapper.getAllTaskNumInTimeByDay();
        Iterator<TaskNumInTimeVO> iterator = result.iterator();
        //将不在开始日期和结束日期之间的任务剔除
        while(iterator.hasNext()) {
            TaskNumInTimeVO next = iterator.next();
            if (next.getTime().compareTo(endTime) > 0 || next.getTime().compareTo(startTime) < 0) {
                iterator.remove();
            }
        }
        return result;
    }

    //查询一段时间内所有任务数量（时间是月）
    @Override
    public List<TaskNumInTimeVO> getAllTaskNumInTimeByMouth(String startTime, String endTime) {
        List<TaskNumInTimeVO> result = taskNumInTimeVOMapper.getAllTaskNumInTimeByMouth();
        Iterator<TaskNumInTimeVO> iterator = result.iterator();
        //将不在开始日期和结束日期之间的任务剔除
        while(iterator.hasNext()) {
            TaskNumInTimeVO next = iterator.next();
            if (next.getTime().compareTo(endTime) > 0 || next.getTime().compareTo(startTime) < 0) {
                iterator.remove();
            }
        }
        return result;
    }

    //查询一段时间内不同类型产品的任务数量（时间是天）
    @Override
    public List<DiffProductTypeAndTaskNumInTimeVO> getDiffProductTypeAndTaskNumInTimeByDay(String startTime, String endTime) {
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
            List<TaskNumInTimeVO> data = taskNumInTimeVOMapper.getProductTypeTaskNumInTimeByIdByDay(productTypeId);
            Iterator<TaskNumInTimeVO> iterator = data.iterator();
            //将不在开始日期和结束日期之间的任务剔除
            while(iterator.hasNext()) {
                TaskNumInTimeVO next = iterator.next();
                if (next.getTime().compareTo(endTime) > 0 || next.getTime().compareTo(startTime) < 0) {
                    iterator.remove();
                }
            }
            //如果该类产品在该时间段内没有任务则不会出现在结果集中
            if(!data.isEmpty()) {
                result.setData(data);
                //插入结果集
                resultList.add(result);
            }
        }
        return resultList;
    }

    //查询一段时间内不同类型产品的任务数量（时间是月）
    @Override
    public List<DiffProductTypeAndTaskNumInTimeVO> getDiffProductTypeAndTaskNumInTimeByMouth(String startTime, String endTime) {
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
            List<TaskNumInTimeVO> data = taskNumInTimeVOMapper.getProductTypeTaskNumInTimeByIdByMouth(productTypeId);
            Iterator<TaskNumInTimeVO> iterator = data.iterator();
            //将不在开始日期和结束日期之间的任务剔除
            while(iterator.hasNext()) {
                TaskNumInTimeVO next = iterator.next();
                if (next.getTime().compareTo(endTime) > 0 || next.getTime().compareTo(startTime) < 0) {
                    iterator.remove();
                }
            }
            //如果该类产品在该时间段内没有任务则不会出现在结果集中
            if(!data.isEmpty()) {
                result.setData(data);
                //插入结果集
                resultList.add(result);
            }
        }
        return resultList;
    }

    //查询一段时间内某个产品任务数量（时间是天）
    @Override
    public List<TaskNumInTimeVO> getProductTaskNumInTimeByPidByDay(int productId, String startTime, String endTime) {
        List<TaskNumInTimeVO> result = taskNumInTimeVOMapper.getProductTaskNumInTimeByPidByDay(productId);
        Iterator<TaskNumInTimeVO> iterator = result.iterator();
        //将不在开始日期和结束日期之间的任务剔除
        while(iterator.hasNext()) {
            TaskNumInTimeVO next = iterator.next();
            if (next.getTime().compareTo(endTime) > 0 || next.getTime().compareTo(startTime) < 0) {
                iterator.remove();
            }
        }
        return result;
    }

    //查询一段时间内某个产品任务数量（时间是月）
    @Override
    public List<TaskNumInTimeVO> getProductTaskNumInTimeByPidByMouth(int productId,String startTime, String endTime) {
        List<TaskNumInTimeVO> result = taskNumInTimeVOMapper.getProductTaskNumInTimeByPidByMouth(productId);
        Iterator<TaskNumInTimeVO> iterator = result.iterator();
        //将不在开始日期和结束日期之间的任务剔除
        while(iterator.hasNext()) {
            TaskNumInTimeVO next = iterator.next();
            if (next.getTime().compareTo(endTime) > 0 || next.getTime().compareTo(startTime) < 0) {
                iterator.remove();
            }
        }
        return result;
    }
}
