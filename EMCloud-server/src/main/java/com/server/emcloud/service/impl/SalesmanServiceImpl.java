package com.server.emcloud.service.impl;

import com.server.emcloud.dao.EquipmentMapper;
import com.server.emcloud.dao.SalesmanMapper;
import com.server.emcloud.domain.Salesman;
import com.server.emcloud.service.EquipmentService;
import com.server.emcloud.service.SalesmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SalesmanServiceImpl implements SalesmanService {
    @Autowired
    SalesmanMapper salesmanMapper;

    @Override
    public List<Salesman> getAll() {
        return salesmanMapper.getAll();
    }

    @Override
    public int  addSalesman(Salesman salesman) {
        return salesmanMapper.insert(salesman);
    }
}
