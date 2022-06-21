package com.server.emcloud.service.impl;


import com.server.emcloud.dao.TestMapper;
import com.server.emcloud.domain.Test;
import com.server.emcloud.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper testMapper;



    @Override
    public List<Test> all() {
        return testMapper.all();
    }
}
