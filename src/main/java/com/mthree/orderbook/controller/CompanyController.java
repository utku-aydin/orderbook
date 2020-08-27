package com.mthree.orderbook.controller;

import com.mthree.orderbook.entity.Company;
import com.mthree.orderbook.service.CompanyService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api")
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
