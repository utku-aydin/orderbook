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
class StockServiceDBTest {

    @Autowired
    private StockService stockService;

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
    void getChangeForStock() {
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

        assertNull(stockService.getChangeForStock(stockRepository.findAll().get(0).getId()));
    }

    @Test
    void getStocksWithChange() {
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
        st.setStock_symbol("AMZN");
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

        OrderId id9 = new OrderId();
        id9.setVersion(0);
        id9.setId(9);
        Order order9 = new Order();
        order9.setId(id9);
        order9.setPrice(new BigDecimal("220"));
        order9.setOrder_size(15);
        order9.setSide(SideEnum.SELL);
        order9.setNumber_matched(0);
        order9.setPlaced_at(LocalDateTime.now(ZoneId.of("GMT")));
        order9.setStatus(StatusEnum.ACTIVE);
        order9.setStock(st);
        order9.setUser(user);
        orderRepository.saveAndFlush(order9);

        OrderId id10 = new OrderId();
        id10.setVersion(0);
        id10.setId(10);
        Order order10 = new Order();
        order10.setId(id10);
        order10.setPrice(new BigDecimal("220"));
        order10.setOrder_size(15);
        order10.setSide(SideEnum.BUY);
        order10.setNumber_matched(0);
        order10.setPlaced_at(LocalDateTime.now(ZoneId.of("GMT")));
        order10.setStatus(StatusEnum.ACTIVE);
        order10.setStock(st);
        order10.setUser(user);
        orderRepository.saveAndFlush(order10);

        Trade trade = new Trade();
        trade.setBuyorder(order6);
        trade.setSellorder(order5);
        trade.setTrade_price(order5.getPrice());
        trade.setTrade_size(15);
        trade.setTrade_time(LocalDateTime.now(ZoneId.of("GMT")).minusDays(1));
        tradeRepository.saveAndFlush(trade);

        Trade trade2 = new Trade();
        trade2.setBuyorder(order7);
        trade2.setSellorder(order8);
        trade2.setTrade_price(order7.getPrice());
        trade2.setTrade_size(15);
        trade2.setTrade_time(LocalDateTime.now(ZoneId.of("GMT")).minusDays(1));
        tradeRepository.saveAndFlush(trade2);

        Trade trade3 = new Trade();
        trade3.setBuyorder(order10);
        trade3.setSellorder(order9);
        trade3.setTrade_price(order9.getPrice());
        trade3.setTrade_size(15);
        trade3.setTrade_time(LocalDateTime.now(ZoneId.of("GMT")));
        tradeRepository.saveAndFlush(trade3);

        assertEquals("AMZN: 220(70)", stockService.getAllStocksWithChange().get(0));
        System.out.println(stockService.getAllStocksWithChange());
    }

    @Test
    void getAllStocks() {
        assertEquals(0, stockService.getAllStocks().size());

        Stock st = new Stock();
        st.setStock_symbol("symb");
        st.setTick_size("0.1");

        Stock st2 = new Stock();
        st2.setStock_symbol("symb");
        st2.setTick_size("0.1");

        stockRepository.saveAndFlush(st);
        stockRepository.saveAndFlush(st2);

        assertEquals(2, stockService.getAllStocks().size());
    }
}