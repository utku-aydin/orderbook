package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.*;
import com.mthree.orderbook.repository.*;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TradeServiceDBTest {

    @Autowired
    private TradeService tradeService;

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
        companyRepository.deleteAll();
        userRepository.deleteAll();
        stockRepository.deleteAll();
        orderRepository.deleteAll();
        tradeRepository.deleteAll();

        OrderId id5 = new OrderId();
        id5.setVersion(0);
        id5.setId(5);
        Order order5 = new Order();
        order5.setId(id5);
        order5.setPrice(new BigDecimal("150"));
        order5.setOrder_size(15);
        order5.setSide(SideEnum.SELL);
        order5.setNumber_matched(0);
        order5.setPlaced_at(LocalDateTime.now(ZoneId.of("GMT")));
        order5.setStatus(StatusEnum.ACTIVE);
        Stock st = new Stock();
        st.setStock_symbol("symb");
        st.setTick_size("0.1");
        stockRepository.saveAndFlush(st);
        User user = new User();
        Company c = new Company();
        c.setCompany_symbol("comp");
        companyRepository.saveAndFlush(c);
        user.setCompany(c);
        user.setUser_symbol("asd");
        userRepository.saveAndFlush(user);
        order5.setStock(st);
        order5.setUser(user);
        orderRepository.saveAndFlush(order5);

        OrderId id6 = new OrderId();
        id6.setVersion(0);
        id6.setId(6);
        Order order6 = new Order();
        order6.setId(id6);
        order6.setPrice(new BigDecimal("150"));
        order6.setOrder_size(15);
        order6.setSide(SideEnum.BUY);
        order6.setNumber_matched(0);
        order6.setPlaced_at(LocalDateTime.now(ZoneId.of("GMT")));
        order6.setStatus(StatusEnum.ACTIVE);
        order6.setStock(st);
        order6.setUser(user);
        orderRepository.saveAndFlush(order6);

        OrderId id7 = new OrderId();
        id7.setVersion(0);
        id7.setId(7);
        Order order7 = new Order();
        order7.setId(id7);
        order7.setPrice(new BigDecimal("150"));
        order7.setOrder_size(15);
        order7.setSide(SideEnum.BUY);
        order7.setNumber_matched(0);
        order7.setPlaced_at(LocalDateTime.now(ZoneId.of("GMT")));
        order7.setStatus(StatusEnum.ACTIVE);
        order7.setStock(st);
        order7.setUser(user);
        orderRepository.saveAndFlush(order7);

        OrderId id8 = new OrderId();
        id8.setVersion(0);
        id8.setId(8);
        Order order8 = new Order();
        order8.setId(id8);
        order8.setPrice(new BigDecimal("150"));
        order8.setOrder_size(15);
        order8.setSide(SideEnum.SELL);
        order8.setNumber_matched(0);
        order8.setPlaced_at(LocalDateTime.now(ZoneId.of("GMT")));
        order8.setStatus(StatusEnum.ACTIVE);
        order8.setStock(st);
        order8.setUser(user);
        orderRepository.saveAndFlush(order8);

        Trade trade = new Trade();
        trade.setBuyorder(order6);
        trade.setSellorder(order5);
        trade.setTrade_price(order5.getPrice());
        trade.setTrade_size(15);
        trade.setTrade_time(LocalDateTime.now(ZoneId.of("GMT")));
        tradeRepository.saveAndFlush(trade);

        Trade trade2 = new Trade();
        trade2.setBuyorder(order7);
        trade2.setSellorder(order8);
        trade2.setTrade_price(order7.getPrice());
        trade2.setTrade_size(15);
        trade2.setTrade_time(LocalDateTime.now(ZoneId.of("GMT")));
        tradeRepository.saveAndFlush(trade2);
    }

    @AfterEach
    void tearDown() {
        companyRepository.deleteAll();
        userRepository.deleteAll();
        stockRepository.deleteAll();
        orderRepository.deleteAll();
        tradeRepository.deleteAll();
    }

    @Test
    public void getPricesWithStep() {
        assertEquals(2, tradeService.getPricesWithStep(1, 2).size());
        assertEquals(new BigDecimal("150"), tradeService.getPricesWithStep(1, 2).get(0));
        assertEquals(new BigDecimal("0"), tradeService.getPricesWithStep(1, 3).get(2));
    }

    @Test
    public void getCountTrades() {
        assertEquals(1, tradeService.getCountTrades(1).size());
        assertEquals(2, tradeService.getCountTrades(2).size());
        assertEquals(2, tradeService.getCountTrades(3).size());
    }
}