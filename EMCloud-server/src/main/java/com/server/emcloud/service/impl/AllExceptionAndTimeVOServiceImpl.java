package com.server.emcloud.service.impl;

import com.server.emcloud.dao.AllExceptionAndTimeVOMapper;
import com.server.emcloud.dao.CompanyMapper;
import com.server.emcloud.domain.Company;
import com.server.emcloud.service.AllExceptionAndTimeVOService;
import com.server.emcloud.service.CompanyService;
import com.server.emcloud.vo.AllExceptionAndTimeVO;
import com.server.emcloud.vo.AllExceptionTypeAndTimeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 王俊博

 */
@Service
public class AllExceptionAndTimeVOServiceImpl implements AllExceptionAndTimeVOService {

    @Autowired
    AllExceptionAndTimeVOMapper allExceptionAndTimeVOMapper;

    @Override
    public List<AllExceptionAndTimeVO> getAllExceptionAndTimeByDay() {
        return allExceptionAndTimeVOMapper.getAllExceptionAndTimeByDay();
    }

    @Override
    public List<AllExceptionAndTimeVO> getAllExceptionAndTimeByMonth() {
        return allExceptionAndTimeVOMapper.getAllExceptionAndTimeByMonth();
    }

    @Override
    public List<AllExceptionTypeAndTimeVO> getAllExceptionTypeAndTimeVOByDay() {
        List<AllExceptionAndTimeVO> baojingByDay = allExceptionAndTimeVOMapper.getBAOJINGByDay();
        List<AllExceptionAndTimeVO> yujingByDay = allExceptionAndTimeVOMapper.getYUJINGByDay();
        List<AllExceptionAndTimeVO> jinjijinggaoByDay = allExceptionAndTimeVOMapper.getJINJIJINGGAOByDay();
        List<AllExceptionTypeAndTimeVO> result = new ArrayList<>();
        AllExceptionTypeAndTimeVO allExceptionTypeAndTimeVO1 = new AllExceptionTypeAndTimeVO();
        AllExceptionTypeAndTimeVO allExceptionTypeAndTimeVO2 = new AllExceptionTypeAndTimeVO();
        AllExceptionTypeAndTimeVO allExceptionTypeAndTimeVO3 = new AllExceptionTypeAndTimeVO();

        allExceptionTypeAndTimeVO1.setName("报警");
        allExceptionTypeAndTimeVO1.setList(baojingByDay);
        allExceptionTypeAndTimeVO2.setName("预警");
        allExceptionTypeAndTimeVO2.setList(yujingByDay);
        allExceptionTypeAndTimeVO3.setName("紧急警告");
        allExceptionTypeAndTimeVO3.setList(jinjijinggaoByDay);
        result.add(allExceptionTypeAndTimeVO1);
        result.add(allExceptionTypeAndTimeVO2);
        result.add(allExceptionTypeAndTimeVO3);
        return result;

    }

    @Override
    public List<AllExceptionTypeAndTimeVO> getAllExceptionTypeAndTimeVOByMonth() {
        List<AllExceptionAndTimeVO> baojingByMonth = allExceptionAndTimeVOMapper.getBAOJINGByMonth();
        List<AllExceptionAndTimeVO> yujingByMonth = allExceptionAndTimeVOMapper.getYUJINGByMonth();
        List<AllExceptionAndTimeVO> jinjijinggaoByMonth = allExceptionAndTimeVOMapper.getJINJIJINGGAOByMonth();
        List<AllExceptionTypeAndTimeVO> result = new ArrayList<>();
        AllExceptionTypeAndTimeVO allExceptionTypeAndTimeVO1 = new AllExceptionTypeAndTimeVO();
        AllExceptionTypeAndTimeVO allExceptionTypeAndTimeVO2 = new AllExceptionTypeAndTimeVO();
        AllExceptionTypeAndTimeVO allExceptionTypeAndTimeVO3 = new AllExceptionTypeAndTimeVO();

        allExceptionTypeAndTimeVO1.setName("报警");
        allExceptionTypeAndTimeVO1.setList(baojingByMonth);
        allExceptionTypeAndTimeVO2.setName("预警");
        allExceptionTypeAndTimeVO2.setList(yujingByMonth);
        allExceptionTypeAndTimeVO3.setName("紧急警告");
        allExceptionTypeAndTimeVO3.setList(jinjijinggaoByMonth);
        result.add(allExceptionTypeAndTimeVO1);
        result.add(allExceptionTypeAndTimeVO2);
        result.add(allExceptionTypeAndTimeVO3);
        return result;
    }
}
