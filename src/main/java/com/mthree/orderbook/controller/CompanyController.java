/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.orderbook.controller;

import com.mthree.orderbook.entity.Company;
import com.mthree.orderbook.entity.Order;
import com.mthree.orderbook.repository.CompanyRepository;
import com.mthree.orderbook.service.CompanyService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author utkua
 */
public class CompanyController {
    
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }
    
    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getBuyOrders() {
        List<Company> companies = companyService.getAllCompanies();
        if (companies.isEmpty()) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        
        return ResponseEntity.ok(companies);
    }
}
