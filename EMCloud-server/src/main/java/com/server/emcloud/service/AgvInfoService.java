package com.server.emcloud.service;

import com.server.emcloud.domain.AgvStateInfo;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/11/0:52
 * @Description:
 */
public interface AgvInfoService {

    //添加设备的一个信息体
    int addAgvStateInfo(AgvStateInfo agvStateInfo);
}
