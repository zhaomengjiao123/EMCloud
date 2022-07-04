package com.server.emcloud.dao;

import com.server.emcloud.domain.Equipment;
import com.server.emcloud.vo.EquipmentErroVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/01/9:26
 * @Description:
 */
@Repository
public interface EquipmentErroMapper {

    //根据公司ID查询所有该公司所有设备的报警信息
    List<EquipmentErroVO> getErroByCid(int company_id);
    //查询全部的报警数量
    int getAllErroCount();

    //监控大屏-查询每个城市的报警数量
    List<Map<String, Object>> getErroCountOfAllCity();

}
