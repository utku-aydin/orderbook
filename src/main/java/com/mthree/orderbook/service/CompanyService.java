package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.Company;
import java.util.List;

public interface CompanyService {

    /**
     * Fetches all companies from the database
     * @return a list of all companies
     */
    List<Company> getAllCompanies();
    
}
