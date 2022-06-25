package com.yanaya.api.company;

import com.yanaya.api.company.entity.Company;
import com.yanaya.api.company.dto.CompanyReq;

import java.util.List;

public interface CompanyService {
    List<Company> findAllCompany();

    Long createCompany(CompanyReq companyReq);
}
