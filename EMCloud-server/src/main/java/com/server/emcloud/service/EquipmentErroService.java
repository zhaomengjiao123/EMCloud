package com.server.emcloud.service;

import com.server.emcloud.vo.EquipmentErroVO;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
