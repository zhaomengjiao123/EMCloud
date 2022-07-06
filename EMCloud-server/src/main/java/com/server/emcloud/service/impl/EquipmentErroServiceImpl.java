package com.server.emcloud.service.impl;

import com.server.emcloud.dao.EquipmentErroMapper;
import com.server.emcloud.service.EquipmentErroService;
import com.server.emcloud.vo.EquipmentErroVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/01/9:31
 * @Description:
 */
@Service
public class EquipmentErroServiceImpl implements EquipmentErroService {

    @Autowired
    private EquipmentErroMapper equipmentErroMapper;

    @Override
    public List<EquipmentErroVO> getErroByCid(int company_id) {
        return equipmentErroMapper.getErroByCid(company_id);
    }

    @Override
    public int getAllErroCount() {
        return equipmentErroMapper.getAllErroCount();
    }

    @Override
    public List<Map<String, Object>> getErroCountOfAllCity() {
        return equipmentErroMapper.getErroCountOfAllCity();
    }

    @Override
    public List<Map<String, Object>> getAllExceptionInfoByCid(int company_id) {
        return equipmentErroMapper.getAllExceptionInfoByCid(company_id);
    }

    @Override
    public List<Map<String, Object>> getAllExceptionInfo() {
        return equipmentErroMapper.getAllExceptionInfo();
    }

    @Override
    public List<EquipmentErroVO> getAllErroInfo() {
        return equipmentErroMapper.getAllErroInfo();
    }
}
