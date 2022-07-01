package com.server.emcloud.service;

import com.server.emcloud.vo.EquipmentWarningVO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/01/1:10
 * @Description:
 */
public interface EquipmentWarningService {


    //得到某公司所有设备的预警信息
    List<EquipmentWarningVO> getWarningByCid(int company_id);
}
