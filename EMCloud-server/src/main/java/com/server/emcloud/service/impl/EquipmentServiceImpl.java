package com.server.emcloud.service.impl;

import com.server.emcloud.dao.EquipmentMapper;
import com.server.emcloud.domain.Equipment;
import com.server.emcloud.service.EquipmentService;
import com.server.emcloud.vo.EquipmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceImpl  implements EquipmentService {
    @Autowired
    EquipmentMapper equipmentMapper;

    @Override
    public List<EquipmentVO> getAll() {
        return equipmentMapper.getAll();
    }

    @Override
    public int update(Equipment equipment) {
        return equipmentMapper.updateByPrimaryKeySelective(equipment);
    }

    @Override
    public EquipmentVO getByPrimaryKey(Integer equipment_id) {
        return equipmentMapper.selectByPrimaryKey(equipment_id);
    }

    @Override
    public int addEquipment(Equipment equipment) {
        return equipmentMapper.addEquipment(equipment)>0 ? 1 : 0;
    }

    @Override
    public int deleteEquipmentByEid(int equipment_id) {
        return equipmentMapper.deleteEquipmentByEid(equipment_id) > 0 ? 1:0;
    }
}