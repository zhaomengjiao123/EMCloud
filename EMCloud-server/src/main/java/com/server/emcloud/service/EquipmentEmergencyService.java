package com.server.emcloud.service;

import com.server.emcloud.vo.EquipmentEmergencyVO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/01/10:11
 * @Description:
 */
public interface EquipmentEmergencyService {
    //根据公司ID查询此公司所有的设备的紧急警告信息
    List<EquipmentEmergencyVO> getEmergencyByCid(int company_id);

    //查询全部的紧急警告信息数量
    int getAllEmergencyCount();

    //查询全部的紧急警告信息
    List<EquipmentEmergencyVO> getAllEmergencyInfo();
}
