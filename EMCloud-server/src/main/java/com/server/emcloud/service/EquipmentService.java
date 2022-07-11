package com.server.emcloud.service;

import com.server.emcloud.domain.Equipment;
import com.server.emcloud.vo.EquipmentCountOfCompanyVO;
import com.server.emcloud.vo.EquipmentNumAndCity;
import com.server.emcloud.vo.EquipmentVO;

import java.util.List;

public interface EquipmentService {
    List<EquipmentVO> getAll();

    int update(Equipment equipment);

    EquipmentVO getByPrimaryKey(Integer equipment_id);

    //添加设备
    public int addEquipment(Equipment equipment);
    //删除设备
    public int deleteEquipmentByEid(int equipment_id);

    // 查询每个城市的设备数量
    List<EquipmentNumAndCity> getAllEquipmentNumAndCity();

    // 获取在线设备数量
    Integer getOnlineNum();

    // 获取不在线设备数量
    Integer getNotOnlineNum();

    // 获取在线设备数量
    Integer getOnlineNum2(String company_id);

    // 获取不在线设备数量
    Integer getNotOnlineNum2(String company_id);

    // 获取每个城市的设备数量
    List<EquipmentCountOfCompanyVO> getEquipmentCountOfCompany();

    //查询某公司所有预警数量
    int getWarningNumByCid(int company_id);

    //查询某公司所有报警数量
    int getErroNumByCid(int company_id);

    //查询某公司所有紧急预警数量
    int getEmergencyNumByCid(int company_id);

    //查询全部的设备数
    int getAllEquipmentCount();

    //查询大屏轮播图设备信息
    List<EquipmentVO> getBigScreenEquipmentInfo();

    //查询有异常的设备的数量
    int getAbnormalEquipmentNum();

}
