package com.server.emcloud.dao;

import com.server.emcloud.vo.EquipmentErroVO;
import org.springframework.stereotype.Repository;

import java.util.List;

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

}
