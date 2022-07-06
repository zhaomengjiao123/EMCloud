package com.server.emcloud.dao;

import com.server.emcloud.vo.EquipmentWarningVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/01/0:45
 * @Description:
 */
@Repository
public interface EquipmentWarningMapper {

    //得到某公司所有设备的预警信息
    List<EquipmentWarningVO> getWarningByCid(int company_id);

    //查询全部的预警信息数量
    int getAllWarningCount();

    //查询全部的预警信息
    List<EquipmentWarningVO> getAllWarningInfo();
}
