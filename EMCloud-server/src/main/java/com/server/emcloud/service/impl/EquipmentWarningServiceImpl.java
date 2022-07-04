package com.server.emcloud.service.impl;

import com.server.emcloud.dao.EquipmentWarningMapper;
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
}
