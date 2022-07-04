package com.server.emcloud.service.impl;
import com.server.emcloud.dao.DepartMapper;
import com.server.emcloud.domain.Depart;
import com.server.emcloud.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  部门
 * @Author: lyx
 * @Date: 2022/7/3
 */

@Service
public class DepartServiceImpl implements DepartService {

    @Autowired
    private DepartMapper departMapper;



    @Override
    public List<Depart> getAllDepart() {
        return departMapper.getAllDepart();
    }

    @Override
    public List<Depart> getDepartByCompany(Integer company_id) {
        return departMapper.getDepartByCompany(company_id);
    }
}
