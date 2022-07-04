package com.server.emcloud.service;
import com.server.emcloud.domain.Depart;

import java.util.List;

/**
 *  部门
 * @Author: lyx
 * @Date: 2022/7/3
 */
public interface DepartService {
    public List<Depart> getAllDepart();//获取所有部门信息

    public List<Depart> getDepartByCompany(Integer company_id);//获取某个公司部门信息
}
