package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.Company;
import com.mthree.orderbook.repository.CompanyRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyServiceDB implements CompanyService {
    
    private final CompanyRepository companyRepository;

    public CompanyServiceDB(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    
    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
    
}
