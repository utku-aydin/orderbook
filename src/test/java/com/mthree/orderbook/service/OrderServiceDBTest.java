package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.*;
import com.mthree.orderbook.repository.*;
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
class OrderServiceDBTest {

    @Autowired
    private OrderService orderService;

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

        OrderId id = new OrderId();
        id.setVersion(0);
        id.setId(1);
        Order order = new Order();
        order.setId(id);
        order.setPrice(new BigDecimal("15"));
        order.setOrder_size(15);
        order.setSide(SideEnum.BUY);
        order.setNumber_matched(0);
        order.setPlaced_at(LocalDateTime.now(ZoneId.of("GMT")));
        order.setStatus(StatusEnum.FULFILLED);
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
        order.setStock(st);
        order.setUser(user);

        OrderId id2 = new OrderId();
        id2.setVersion(0);
        id2.setId(2);
        Order order2 = new Order();
        order2.setId(id2);
        order2.setPrice(new BigDecimal("15"));
        order2.setOrder_size(15);
        order2.setSide(SideEnum.BUY);
        order2.setNumber_matched(0);
        order2.setPlaced_at(LocalDateTime.now(ZoneId.of("GMT")));
        order2.setStatus(StatusEnum.ACTIVE);
        order2.setStock(st);
        order2.setUser(user);

        OrderId id3 = new OrderId();
        id3.setVersion(0);
        id3.setId(3);
        Order order3 = new Order();
        order3.setId(id3);
        order3.setPrice(new BigDecimal("150"));
        order3.setOrder_size(15);
        order3.setSide(SideEnum.SELL);
        order3.setNumber_matched(0);
        order3.setPlaced_at(LocalDateTime.now(ZoneId.of("GMT")));
        order3.setStatus(StatusEnum.ACTIVE);
        order3.setStock(st);
        order3.setUser(user);

        OrderId id4 = new OrderId();
        id4.setVersion(0);
        id4.setId(4);
        Order order4 = new Order();
        order4.setId(id4);
        order4.setPrice(new BigDecimal("150"));
        order4.setOrder_size(15);
        order4.setSide(SideEnum.SELL);
        order4.setNumber_matched(0);
        order4.setPlaced_at(LocalDateTime.now(ZoneId.of("GMT")));
        order4.setStatus(StatusEnum.FULFILLED);
        order4.setStock(st);
        order4.setUser(user);

        orderRepository.saveAndFlush(order);
        orderRepository.saveAndFlush(order2);
        orderRepository.saveAndFlush(order3);
        orderRepository.saveAndFlush(order4);
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
    public void getOrders() {
        assertEquals(4, orderService.getOrders().size());
    }

    @Test
    public void getBuyOrders() {
        assertEquals(2, orderService.getBuyOrders().size());
    }

    @Test
    public void getSellOrders() {
        assertEquals(2, orderService.getSellOrders().size());
    }

    @Test
    public void getActiveBuyOrders() {
        assertEquals(1, orderService.getActiveBuyOrders().size());
    }

    @Test
    public void getActiveSellOrders() {
        assertEquals(1, orderService.getActiveSellOrders().size());
    }

    @Test
    public void getOrderByID() {
        OrderId id5 = new OrderId();
        id5.setVersion(0);
        id5.setId(5);
        Order order5 = new Order();
        order5.setId(id5);
        order5.setPrice(new BigDecimal("150.00"));
        order5.setOrder_size(15);
        order5.setSide(SideEnum.SELL);
        order5.setNumber_matched(0);
        order5.setPlaced_at(LocalDateTime.now(ZoneId.of("GMT")));
        order5.setStatus(StatusEnum.FULFILLED);
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

        assertEquals(orderService.getOrderHistory(5).get(0).getId(), order5.getId());
    }

    @Test
    public void addOrder() {

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
        order5.setStatus(StatusEnum.FULFILLED);
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

        assertEquals(5, orderService.getOrders().size());
    }

    @Test
    public void cancelOrderByID() {
        int activeOrders = orderService.getActiveBuyOrders().size() + orderService.getActiveSellOrders().size();
        orderService.cancelOrderByID(3, 0);
        assertNotEquals(activeOrders, orderService.getActiveSellOrders().size() + orderService.getActiveBuyOrders().size());
    }
}