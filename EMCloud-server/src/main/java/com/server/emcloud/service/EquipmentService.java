package com.server.emcloud.service;

import com.server.emcloud.domain.Equipment;
import com.server.emcloud.vo.EquipmentVO;

import java.util.List;

public interface EquipmentService {
    List<EquipmentVO> getAll();

    int update(Equipment equipment);

    EquipmentVO getByPrimaryKey(Integer equipment_id);
}
