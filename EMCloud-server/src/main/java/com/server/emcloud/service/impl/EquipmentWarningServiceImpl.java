package com.server.emcloud.service.impl;

import com.server.emcloud.dao.EquipmentWarningMapper;
import com.server.emcloud.domain.EquipmentWarning;
import com.server.emcloud.service.EquipmentWarningService;
import com.server.emcloud.vo.EquipmentWarningVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/01/1:11
 * @Description:
 */
@Service
public class EquipmentWarningServiceImpl implements EquipmentWarningService {

    @Autowired
    private EquipmentWarningMapper equipmentWarningMapper;

    @Override
    public List<EquipmentWarningVO> getWarningByCid(int company_id) {
        return equipmentWarningMapper.getWarningByCid(company_id);
    }

    @Override
    public int getAllWarningCount() {
        return equipmentWarningMapper.getAllWarningCount();
    }

    @Override
    public List<EquipmentWarningVO> getAllWarningInfo() {
        return equipmentWarningMapper.getAllWarningInfo();
    }

    @Override
    public List<EquipmentWarningVO> getWarningByEquipmentNumberAndCid(int company_id, String equipment_number) {
        return equipmentWarningMapper.getWarningByEquipmentNumberAndCid(company_id,equipment_number);
    }

    @Override
    public int addWarningInfo(EquipmentWarning equipmentWarning) {
        return equipmentWarningMapper.addWarningInfo(equipmentWarning);
    }
}
