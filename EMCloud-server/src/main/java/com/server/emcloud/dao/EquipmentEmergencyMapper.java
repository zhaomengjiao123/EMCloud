package com.server.emcloud.dao;

import com.server.emcloud.vo.EquipmentEmergencyVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/01/10:09
 * @Description:
 */
@Repository
public interface EquipmentEmergencyMapper {
    //根据公司ID查询此公司所有的设备的紧急警告信息
    List<EquipmentEmergencyVO> getEmergencyByCid(int company_id);
}