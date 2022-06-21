package com.server.emcloud.dao;

import com.server.emcloud.domain.Test;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestMapper {


    //查询全部用户
    public List<Test> all();





}

