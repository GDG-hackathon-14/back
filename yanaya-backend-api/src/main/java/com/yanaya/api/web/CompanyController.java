package com.yanaya.api.web;

import com.yanaya.api.company.entity.Company;
import com.yanaya.api.company.CompanyService;
import com.yanaya.api.company.dto.CompanyReq;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> findCompanies() {
        return new ResponseEntity<>(companyService.findAllCompany(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Long> saveCompany(@Valid @RequestBody CompanyReq companyReq) {
        return new ResponseEntity<>(companyService.createCompany(companyReq), HttpStatus.CREATED);
    }
}
