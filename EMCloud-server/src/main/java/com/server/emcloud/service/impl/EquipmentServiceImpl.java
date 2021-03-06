package com.server.emcloud.service.impl;

import com.server.emcloud.dao.EquipmentMapper;
import com.server.emcloud.domain.Equipment;
import com.server.emcloud.service.EquipmentService;
import com.server.emcloud.vo.EquipmentCountOfCompanyVO;
import com.server.emcloud.vo.EquipmentNumAndCity;
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

    @Override
    public List<EquipmentNumAndCity> getAllEquipmentNumAndCity() {
        return equipmentMapper.getAllEquipmentNumAndCity();
    }

    @Override
    public Integer getOnlineNum() {
        return equipmentMapper.getOnlineNum();
    }

    @Override
    public Integer getNotOnlineNum() {
        return equipmentMapper.getNotOnlineNum();
    }

    @Override
    public Integer getOnlineNum2(String company_id) {
        return equipmentMapper.getOnlineNum2(company_id);
    }

    @Override
    public Integer getNotOnlineNum2(String company_id) {
        return equipmentMapper.getNotOnlineNum2(company_id);
    }

    @Override
    public List<EquipmentCountOfCompanyVO> getEquipmentCountOfCompany() {
        return equipmentMapper.getEquipmentCountOfCompany();
    }

    @Override
    public int getWarningNumByCid(int company_id) {
        return equipmentMapper.getWarningNumByCid(company_id);
    }

    @Override
    public int getErroNumByCid(int company_id) {
        return equipmentMapper.getErroNumByCid(company_id);
    }

    @Override
    public int getEmergencyNumByCid(int company_id) {
        return equipmentMapper.getEmergencyNumByCid(company_id);
    }

    @Override
    public int getAllEquipmentCount() {
        return equipmentMapper.getAllEquipmentCount();
    }

    @Override
    public List<EquipmentVO> getBigScreenEquipmentInfo() {
        return equipmentMapper.getBigScreenEquipmentInfo();
    }

    @Override
    public int getAbnormalEquipmentNum() {
        return equipmentMapper.getAbnormalEquipmentNum();
    }

    @Override
    public List<Equipment> getEquipmentNumberListByCid(int company_id) {
        return equipmentMapper.getEquipmentNumberListByCid(company_id);
    }

    @Override
    public int updateErroCountByEid(int equipment_id) {
        return equipmentMapper.updateErroCountByEid(equipment_id);
    }

    @Override
    public int updateWarningCountByEid(int equipment_id) {
        return equipmentMapper.updateWarningCountByEid(equipment_id);
    }
}
