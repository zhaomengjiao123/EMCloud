package com.server.emcloud.dao;

import com.server.emcloud.domain.Salesman;
 import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SalesmanMapper {

    int deleteByPrimaryKey(Integer salesman_id);

    int insert(Salesman record);

    int insertSelective(Salesman record);


    Salesman selectByPrimaryKey(Integer salesman_id);

    List<Salesman> getAll();

    int updateByPrimaryKeySelective(Salesman record);

    int updateByPrimaryKey(Salesman record);
}