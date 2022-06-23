package com.server.emcloud.service;

import com.server.emcloud.domain.Equipment;
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
}
