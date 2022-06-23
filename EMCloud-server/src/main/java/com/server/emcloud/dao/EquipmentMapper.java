package com.server.emcloud.dao;

import com.server.emcloud.domain.Equipment;
 import java.util.List;

import com.server.emcloud.vo.EquipmentVO;
import org.apache.ibatis.annotations.Param;

public interface EquipmentMapper {


    int deleteByPrimaryKey(Integer equipment_id);

    int insert(Equipment record);

    int insertSelective(Equipment record);


    EquipmentVO selectByPrimaryKey(Integer equipment_id);



    int updateByPrimaryKeySelective(Equipment record);

    int updateByPrimaryKey(Equipment record);

    List<EquipmentVO> getAll();
}