package com.server.emcloud.dao;

import com.server.emcloud.domain.Depart;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  部门
 * @Author: lyx
 * @Date: 2022/7/3
 */

@Repository
public interface DepartMapper {
    public List<Depart> getAllDepart();

    public List<Depart> getDepartByCompany(Integer company_id);
}
