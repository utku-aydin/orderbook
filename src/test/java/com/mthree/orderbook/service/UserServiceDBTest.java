package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.Company;
import com.mthree.orderbook.entity.User;
import com.mthree.orderbook.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceDBTest {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private TradeRepository tradeRepository;

    @BeforeEach
    public void setUp() {
        tradeRepository.deleteAll();
        orderRepository.deleteAll();
        userRepository.deleteAll();
        companyRepository.deleteAll();
        stockRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        tradeRepository.deleteAll();
        orderRepository.deleteAll();
        userRepository.deleteAll();
        companyRepository.deleteAll();
        stockRepository.deleteAll();
    }

    @Test
    void getAllUsers() {
        assertTrue(userService.getAllUsers().isEmpty());

        User user = new User();
        Company c = new Company();
        c.setCompany_symbol("comp");
        companyRepository.saveAndFlush(c);
        user.setCompany(c);
        user.setUser_symbol("asd");
        userRepository.saveAndFlush(user);

        User user2 = new User();
        user2.setCompany(c);
        user2.setUser_symbol("dsa");
        userRepository.saveAndFlush(user2);

        assertEquals(2, userService.getAllUsers().size());
    }
}