package com.server.emcloud.controller;

import com.server.emcloud.domain.AgvStateInfo;
import com.server.emcloud.service.AgvInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/11/0:55
 * @Description:
 */
@RestController
public class AgvInfoController {
    @Autowired
    private AgvInfoService agvInfoService;

    public int addAgvStateInfoController(AgvStateInfo agvStateInfo){
        return agvInfoService.addAgvStateInfo(agvStateInfo);
    }


}
