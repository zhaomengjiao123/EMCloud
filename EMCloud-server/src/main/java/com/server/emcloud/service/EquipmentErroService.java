package com.server.emcloud.service;

import com.server.emcloud.domain.EquipmentErro;
import com.server.emcloud.vo.EquipmentErroVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/01/9:29
 * @Description:
 */
public interface EquipmentErroService {

    //根据公司ID查询所有该公司所有设备的报警信息
    List<EquipmentErroVO> getErroByCid(int company_id);

    //查询全部的报警数量
    int getAllErroCount();

    //监控大屏-查询每个城市的报警数量
    List<Map<String, Object>> getErroCountOfAllCity();

    //查询某公司的全部预警/报警/紧急警告信息
    List<Map<String,Object>> getAllExceptionInfoByCid(int company_id);

    //查询全部异常信息
    List<Map<String,Object>> getAllExceptionInfo();

    //查询全部的报警信息
    List<EquipmentErroVO> getAllErroInfo();

    //根据设备编号查询设备所有报警信息
    List<EquipmentErroVO> getErroByEquipmentNumberAndCid(int company_id,String equipment_number);
    //添加报警信息
    int addErroInfo(EquipmentErro equipmentErro);
}
