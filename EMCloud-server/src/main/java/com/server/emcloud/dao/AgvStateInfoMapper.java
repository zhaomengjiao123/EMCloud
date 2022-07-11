package com.server.emcloud.dao;

import com.server.emcloud.domain.AgvStateInfo;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/09/22:18
 * @Description:
 */
@Repository
public interface AgvStateInfoMapper {

    //添加设备的一个信息体
    int addAgvStateInfo(AgvStateInfo agvStateInfo);
}
