package com.server.emcloud.service;

import com.server.emcloud.domain.Company;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/06/22/15:41
 * @Description: company 服务类
 */
public interface CompanyService {
    //查询全部公司
    public List<Company> getAllCompany();

    //添加公司
    public int addCompany(Company company);

    //删除公司
    public int deleteCompanyOfCid(int company_id);

    //更新公司信息（可以传入Company类的部分属性）
    int updateByPrimaryKeySelective(Company record);

}
