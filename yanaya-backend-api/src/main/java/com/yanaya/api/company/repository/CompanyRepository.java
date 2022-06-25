package com.yanaya.api.company.repository;

import com.yanaya.api.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long>{
    Optional<Company> findByCompId(Long compId);
}
