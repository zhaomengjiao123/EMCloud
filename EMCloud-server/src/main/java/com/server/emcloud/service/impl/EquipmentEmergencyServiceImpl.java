package com.server.emcloud.service.impl;

import com.server.emcloud.dao.EquipmentEmergencyMapper;
import com.server.emcloud.service.EquipmentEmergencyService;
import com.server.emcloud.vo.EquipmentEmergencyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/01/10:12
 * @Description:
 */
@Service
public class EquipmentEmergencyServiceImpl implements EquipmentEmergencyService {

    @Autowired
    private EquipmentEmergencyMapper equipmentEmergencyMapper;


    @Override
    public List<EquipmentEmergencyVO> getEmergencyByCid(int company_id) {
        return equipmentEmergencyMapper.getEmergencyByCid(company_id);
    }

    @Override
    public int getAllEmergencyCount() {
        return equipmentEmergencyMapper.getAllEmergencyCount();
    }

    @Override
    public List<EquipmentEmergencyVO> getAllEmergencyInfo() {
        return equipmentEmergencyMapper.getAllEmergencyInfo();
    }

    @Override
    public List<EquipmentEmergencyVO> getEmergencyByEquipmentNumberAndCid(int company_id, String equipment_number) {
        return equipmentEmergencyMapper.getEmergencyByEquipmentNumberAndCid(company_id,equipment_number);
    }
}
