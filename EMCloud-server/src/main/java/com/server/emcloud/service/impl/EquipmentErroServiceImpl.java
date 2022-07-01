package com.server.emcloud.service.impl;

import com.server.emcloud.dao.EquipmentErroMapper;
import com.server.emcloud.service.EquipmentErroService;
import com.server.emcloud.vo.EquipmentErroVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
