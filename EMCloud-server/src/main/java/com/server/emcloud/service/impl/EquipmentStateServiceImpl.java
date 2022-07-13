package com.server.emcloud.service.impl;

import com.server.emcloud.dao.EquipmentStateMapper;
import com.server.emcloud.domain.EquipmentState;
import com.server.emcloud.service.EquipmentStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/13/18:35
 * @Description:
 */
@Service
public class EquipmentStateServiceImpl implements EquipmentStateService {
    @Autowired
    private EquipmentStateMapper equipmentStateMapper;
    @Override
    public int getEquipmentStateIDCount(int agvid) {
        System.out.println("Count:"+equipmentStateMapper.getEquipmentStateIDCount(agvid));
        return equipmentStateMapper.getEquipmentStateIDCount(agvid);
    }

    @Override
    public int updateEquipmentState(EquipmentState equipmentState) {
        return equipmentStateMapper.updateEquipmentState(equipmentState);
    }

    @Override
    public int addEquipmentState(EquipmentState equipmentState) {
        return equipmentStateMapper.addEquipmentState(equipmentState);
    }

    @Override
    public List<Map<String, Object>> getEquipmentListOfUpdateTime() {
        return equipmentStateMapper.getEquipmentListOfUpdateTime();
    }

    @Override
    public List<Map<String, Object>> getStateInfoByEidAndTime(int equipment_id, String state_update_time) {
        return equipmentStateMapper.getStateInfoByEidAndTime(equipment_id,state_update_time);
    }

    @Override
    public List<Map<String, Object>> getEquipmentListOfUpdateTimeByCid(int company_id) {
        return equipmentStateMapper.getEquipmentListOfUpdateTimeByCid(company_id);
    }

    @Override
    public List<Map<String, Object>> getEquipmentListOfUpdateTimeByCidAndEn(int company_id, String equipment_number) {
        return equipmentStateMapper.getEquipmentListOfUpdateTimeByCidAndEn(company_id,equipment_number);
    }
}
