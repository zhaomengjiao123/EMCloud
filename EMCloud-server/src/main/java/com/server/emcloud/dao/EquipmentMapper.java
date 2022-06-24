package com.server.emcloud.dao;

import com.server.emcloud.domain.Equipment;
 import java.util.List;

import com.server.emcloud.vo.EquipmentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentMapper {


    int deleteByPrimaryKey(Integer equipment_id);

    int insert(Equipment record);

    int insertSelective(Equipment record);


    EquipmentVO selectByPrimaryKey(Integer equipment_id);



    int updateByPrimaryKeySelective(Equipment record);

    int updateByPrimaryKey(Equipment record);

    List<EquipmentVO> getAll();

    //添加设备
    public int addEquipment(Equipment equipment);
    //删除设备
    public int deleteEquipmentByEid(int equipment_id);
}