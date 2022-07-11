package com.server.emcloud.service.impl;

import com.server.emcloud.dao.AgvStateInfoMapper;
import com.server.emcloud.domain.AgvStateInfo;
import com.server.emcloud.service.AgvInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/11/0:52
 * @Description:
 */
@Service
public class AgvInfoSErviceImpl implements AgvInfoService {

    @Autowired
    private AgvStateInfoMapper agvStateInfoMapper;

    @Override
    public int addAgvStateInfo(AgvStateInfo agvStateInfo) {
        return agvStateInfoMapper.addAgvStateInfo(agvStateInfo);
    }
}
