package com.server.emcloud.service.impl;

import com.server.emcloud.dao.CompanyMapper;
import com.server.emcloud.domain.Company;
import com.server.emcloud.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/06/22/15:44
 * @Description: company 服务实现类
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    //查询全部公司
    @Override
    public List<Company> getAllCompany() {
        System.out.println("get all company successful!");
        return companyMapper.getAllCompany();
    }

    //添加公司 1 为操作成功  0为操作失败
    @Override
    public int addCompany(Company company) {
        return companyMapper.addCompany(company)>0 ? 1 : 0;
    }

    //删除公司
    @Override
    public int deleteCompanyOfCid(int company_id) {
        return companyMapper.deleteCompanyOfCid(company_id)>0 ? 1 : 0;
    }


    //更新公司
    @Override
    public int updateByPrimaryKeySelective(Company record){
        return companyMapper.updateByPrimaryKeySelective(record);
    }



}
