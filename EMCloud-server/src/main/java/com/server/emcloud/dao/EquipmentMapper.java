package com.server.emcloud.dao;

import com.server.emcloud.domain.Equipment;
 import java.util.List;

import com.server.emcloud.vo.EquipmentCountOfCompanyVO;
import com.server.emcloud.vo.EquipmentNumAndCity;
import com.server.emcloud.vo.EquipmentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentMapper {



    EquipmentVO selectByPrimaryKey(Integer equipment_id);


    //查询每个公司的所有设备数
    List<EquipmentCountOfCompanyVO> getEquipmentCountOfCompany();

    int updateByPrimaryKeySelective(Equipment record);

    int updateByPrimaryKey(Equipment record);

    List<EquipmentVO> getAll();

    //添加设备
    public int addEquipment(Equipment equipment);
    //删除设备
    public int deleteEquipmentByEid(int equipment_id);

    // 查询每个城市的设备总数
    List<EquipmentNumAndCity> getAllEquipmentNumAndCity();

    Integer getOnlineNum();

    Integer getNotOnlineNum();

    Integer getOnlineNum2(String company_id);

    Integer getNotOnlineNum2(String company_id);
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

    //查询某公司的所有设备编号
    List<Equipment> getEquipmentNumberListByCid(int company_id);
    //更新设备报警数量
    int updateErroCountByEid(int equipment_id);
    //更新设备的预警数量
    int updateWarningCountByEid(int equipment_id);
}