package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.Company;
import com.mthree.orderbook.repository.CompanyRepository;
import com.mthree.orderbook.repository.OrderRepository;
import com.mthree.orderbook.repository.TradeRepository;
import com.mthree.orderbook.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CompanyServiceDBTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private CompanyService companyService;

    @BeforeEach
    public void setUp() {
        tradeRepository.deleteAll();
        orderRepository.deleteAll();
        userRepository.deleteAll();
        companyRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        tradeRepository.deleteAll();
        orderRepository.deleteAll();
        userRepository.deleteAll();
        companyRepository.deleteAll();
    }

    @Test
    public void getCompanies() {
        Company c = new Company();
        c.setCompany_symbol("comp");
        companyRepository.saveAndFlush(c);

        assertEquals(1, companyService.getAllCompanies().size());

        Company c1 = companyService.getAllCompanies().get(0);

        assertEquals(c, c1);
    }
}