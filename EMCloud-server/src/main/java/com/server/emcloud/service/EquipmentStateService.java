package com.server.emcloud.service;

import com.server.emcloud.domain.EquipmentState;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/13/18:34
 * @Description:
 */
public interface EquipmentStateService {

    //查询状态表中是否存在对应ID的设备
    int getEquipmentStateIDCount(int agvid);
    //更新状态信息
    int updateEquipmentState(EquipmentState equipmentState);
    //添加状态信息
    int addEquipmentState(EquipmentState equipmentState);
    //查询更新时间设备列表
    List<Map<String,Object>> getEquipmentListOfUpdateTime();
    //查询一个具体的状态信息
    List<Map<String,Object>> getStateInfoByEidAndTime(int equipment_id,String state_update_time);
    //查询某公司的设备实时更新时间
    List<Map<String,Object>> getEquipmentListOfUpdateTimeByCid(int company_id);
    //根据设备编号查询某公司的设备实时更新列表
    List<Map<String,Object>> getEquipmentListOfUpdateTimeByCidAndEn(int company_id,String equipment_number);
}
