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

    //查询一段时间内某公司所有任务数量（时间是天）
    @Override
    public List<TaskNumInTimeVO> getAllTaskNumInTimeByDay2(String startTime, String endTime, int company_id) {
        List<TaskNumInTimeVO> result = taskNumInTimeVOMapper.getAllTaskNumInTimeByDay2(company_id);
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

    //查询一段时间内某公司所有任务数量（时间是月）
    @Override
    public List<TaskNumInTimeVO> getAllTaskNumInTimeByMouth2(String startTime, String endTime, int company_id) {
        List<TaskNumInTimeVO> result = taskNumInTimeVOMapper.getAllTaskNumInTimeByMouth2(company_id);
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

        //查询所有产品类型id
        List<Integer> typeIdList = taskNumInTimeVOMapper.getAllProductId();

        //循环得到的每个产品类型id
        for (Integer productTypeId : typeIdList) {
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
            result.setData(data);
            //插入结果集
            resultList.add(result);
        }
        return resultList;
    }

    //查询一段时间内某公司不同类型产品的任务数量（时间是天）
    @Override
    public List<DiffProductTypeAndTaskNumInTimeVO> getDiffProductTypeAndTaskNumInTimeByDay2(String startTime, String endTime, int company_id) {
        List<DiffProductTypeAndTaskNumInTimeVO> resultList = new ArrayList<>();

        //查询所有产品类型id
        List<Integer> typeIdList = taskNumInTimeVOMapper.getAllProductId();

        //循环得到的每个产品类型id
        for (Integer productTypeId : typeIdList) {
            //构建返回结果
            DiffProductTypeAndTaskNumInTimeVO result = new DiffProductTypeAndTaskNumInTimeVO();
            //根据产品类型id获得产品类型名称
            result.setName(taskNumInTimeVOMapper.getProductNameById(productTypeId));
            //根据产品类型id获得该产品类型一段时间内的任务数量
            List<TaskNumInTimeVO> data = taskNumInTimeVOMapper.getProductTypeTaskNumInTimeByIdByDay2(productTypeId, company_id);
            Iterator<TaskNumInTimeVO> iterator = data.iterator();
            //将不在开始日期和结束日期之间的任务剔除
            while(iterator.hasNext()) {
                TaskNumInTimeVO next = iterator.next();
                if (next.getTime().compareTo(endTime) > 0 || next.getTime().compareTo(startTime) < 0) {
                    iterator.remove();
                }
            }
            result.setData(data);
            //插入结果集
            resultList.add(result);
        }
        return resultList;
    }

    //查询一段时间内不同类型产品的任务数量（时间是月）
    @Override
    public List<DiffProductTypeAndTaskNumInTimeVO> getDiffProductTypeAndTaskNumInTimeByMouth(String startTime, String endTime) {
        List<DiffProductTypeAndTaskNumInTimeVO> resultList = new ArrayList<>();

        //查询所有产品类型id
        List<Integer> typeIdList = taskNumInTimeVOMapper.getAllProductId();

        //循环得到的每个产品类型id
        for (Integer productTypeId : typeIdList) {
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
            result.setData(data);
            //插入结果集
            resultList.add(result);
        }
        return resultList;
    }

    //查询一段时间内某公司不同类型产品的任务数量（时间是月）
    @Override
    public List<DiffProductTypeAndTaskNumInTimeVO> getDiffProductTypeAndTaskNumInTimeByMouth2(String startTime, String endTime, int company_id) {
        List<DiffProductTypeAndTaskNumInTimeVO> resultList = new ArrayList<>();

        //查询所有产品类型id
        List<Integer> typeIdList = taskNumInTimeVOMapper.getAllProductId();

        //循环得到的每个产品类型id
        for (Integer productTypeId : typeIdList) {
            //构建返回结果
            DiffProductTypeAndTaskNumInTimeVO result = new DiffProductTypeAndTaskNumInTimeVO();
            //根据产品类型id获得产品类型名称
            result.setName(taskNumInTimeVOMapper.getProductNameById(productTypeId));
            //根据产品类型id获得该产品类型一段时间内的任务数量
            List<TaskNumInTimeVO> data = taskNumInTimeVOMapper.getProductTypeTaskNumInTimeByIdByMouth2(productTypeId, company_id);
            Iterator<TaskNumInTimeVO> iterator = data.iterator();
            //将不在开始日期和结束日期之间的任务剔除
            while(iterator.hasNext()) {
                TaskNumInTimeVO next = iterator.next();
                if (next.getTime().compareTo(endTime) > 0 || next.getTime().compareTo(startTime) < 0) {
                    iterator.remove();
                }
            }
            result.setData(data);
            //插入结果集
            resultList.add(result);
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

    //查询一段时间内某公司某个产品任务数量（时间是天）
    @Override
    public List<TaskNumInTimeVO> getProductTaskNumInTimeByPidByDay2(int productId, String startTime, String endTime, int company_id) {
        List<TaskNumInTimeVO> result = taskNumInTimeVOMapper.getProductTaskNumInTimeByPidByDay2(productId, company_id);
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

    //查询一段时间内某公司某个产品任务数量（时间是月）
    @Override
    public List<TaskNumInTimeVO> getProductTaskNumInTimeByPidByMouth2(int productId,String startTime, String endTime, int company_id) {
        List<TaskNumInTimeVO> result = taskNumInTimeVOMapper.getProductTaskNumInTimeByPidByMouth2(productId, company_id);
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
