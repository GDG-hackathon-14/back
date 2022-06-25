package com.yanaya.api.company;

import com.yanaya.api.company.entity.Company;
import com.yanaya.api.company.repository.CompanyRepository;
import com.yanaya.api.company.dto.CompanyReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    public Long createCompany(CompanyReq companyReq) {
        Company companyEntity = Company.builder()
                .compName(companyReq.getCompName())
                .build();
        return companyRepository.save(companyEntity).getCompId();
    }


}
