package com.server.emcloud.dao;

import com.server.emcloud.vo.EquipmentEmergencyVO;
import com.server.emcloud.vo.EquipmentErroVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/01/10:09
 * @Description:
 */
@Repository
public interface EquipmentEmergencyMapper {
    //根据公司ID查询此公司所有的设备的紧急警告信息
    List<EquipmentEmergencyVO> getEmergencyByCid(int company_id);

    //查询全部的紧急警告信息数量
    int getAllEmergencyCount();

    //查询全部的紧急警告信息
    List<EquipmentEmergencyVO> getAllEmergencyInfo();

    //根据设备编号查询设备所有紧急报警信息
    List<EquipmentEmergencyVO> getEmergencyByEquipmentNumberAndCid(int company_id, String equipment_number);
}
