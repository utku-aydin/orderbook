/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.Order;
import com.mthree.orderbook.entity.OrderId;
import com.mthree.orderbook.entity.SideEnum;
import com.mthree.orderbook.entity.StatusEnum;
import com.mthree.orderbook.entity.Stock;
import com.mthree.orderbook.entity.Trade;
import com.mthree.orderbook.entity.User;
import com.mthree.orderbook.repository.CompanyRepository;
import com.mthree.orderbook.repository.OrderRepository;
import com.mthree.orderbook.repository.StockRepository;
import com.mthree.orderbook.repository.TradeRepository;
import com.mthree.orderbook.repository.UserRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 *
 * @author utkua
 */
@Repository
public class OrderServiceDB implements OrderService {
    
    private final OrderRepository orderRepository;
    private final TradeRepository tradeRepository;
    private final StockRepository stockRepository;
    private final UserRepository userRepository;

    public OrderServiceDB(OrderRepository orderRepository, TradeRepository tradeRepository, StockRepository stockRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.tradeRepository = tradeRepository;
        this.stockRepository = stockRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
    
    @Override
    public List<Order> getBuyOrders() {
        return orderRepository.findBuyOrders();
    }

    @Override
    public List<Order> getSellOrders() {
        return orderRepository.findSellOrders();
    }
    
    @Override
    public List<Order> getActiveBuyOrders() {
        return orderRepository.findActiveBuyOrders();
    }

    @Override
    public List<Order> getActiveSellOrders() {
        return orderRepository.findActiveSellOrders();
    }

    @Override
    public Order getOrderByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order addOrder(Map<String, String> orderData) {
        OrderId id = new OrderId();
        id.setVersion(0);
        if (orderRepository.findHighestId().isEmpty()) {
            System.out.println("Nully");
            id.setId(1);
        } else {
            id.setId(orderRepository.findHighestId().get(0).getId().getId() + 1);
        }
        
        Order order = new Order();
        order.setId(id);
        order.setPrice(new BigDecimal(orderData.get("price")));
        order.setOrder_size(Integer.parseInt(orderData.get("order_size")));
        order.setSide(SideEnum.valueOf(orderData.get("side")));
        order.setNumber_matched(Integer.parseInt(orderData.get("number_matched")));
        order.setPlaced_at(LocalDateTime.now(ZoneId.of("GMT")));
        order.setStatus(StatusEnum.valueOf(orderData.get("status")));
        
        // ERROR CHECK
        Stock stock = stockRepository.findById(Integer.parseInt(orderData.get("stock_id"))).orElse(null);
        User user = userRepository.findById(Integer.parseInt(orderData.get("usr_id"))).orElse(null);
        
        order.setStock(stock);
        order.setUser(user);
        
        //companyRepository.saveAndFlush(order.getUser().getCompany());
        //userRepository.saveAndFlush(order.getUser());
        order = orderRepository.saveAndFlush(order);
        System.out.println("Order id set: " + order.getId());
        
        if (order.getSide() == SideEnum.BUY) {
            matchBuyOrder(order);
        } else {
            matchSellOrder(order);
        }
        
        return order;
    }

    @Override
    public Order updateOrder(Map<String, String> orderData) {
        OrderId id = new OrderId();
        
        id.setId(Integer.parseInt(orderData.get("id")));
        id.setVersion(Integer.parseInt(orderData.get("version")));
        
        Order order = new Order();
        order.setId(id);
        order.setPrice(new BigDecimal(orderData.get("price")));
        order.setOrder_size(Integer.parseInt(orderData.get("order_size")));
        order.setSide(SideEnum.valueOf(orderData.get("side")));
        order.setNumber_matched(Integer.parseInt(orderData.get("number_matched")));
        order.setPlaced_at(LocalDateTime.parse(orderData.get("placed_at")));
        order.setStatus(StatusEnum.valueOf(orderData.get("status")));
        
        // ERROR CHECK
        Stock stock = stockRepository.findById(Integer.parseInt(orderData.get("stock_id"))).orElse(null);
        User user = userRepository.findById(Integer.parseInt(orderData.get("usr_id"))).orElse(null);
        
        order.setStock(stock);
        order.setUser(user);
        
        order = orderRepository.saveAndFlush(order);
        System.out.println("Order id set: " + order.getId());
        
        return order;
    }

    @Override
    public Order cancelOrderByID(int id) {
        // ERROR CHECK
        Order order = orderRepository.findById(id).orElse(null);
        order.setStatus(StatusEnum.CANCELLED);
        order = orderRepository.saveAndFlush(order);
        
        return order;
    }
    
    private void matchBuyOrder(Order order) {
        List<Order> compared = orderRepository.findActiveSellOrders();
        int orderRemaining = order.getOrder_size() - order.getNumber_matched();
        int marker = 0;
        
        Order current;
        if (compared.size() > 0) {
            System.out.println("Current has something here");
            current = compared.get(marker);
        } else {
            System.out.println("Exit here");
            return;
        }
        
        while (orderRemaining > 0) {
            current = compared.get(marker);
            Trade trade = matchOrders(order, current);
            if (trade != null) {
                System.out.println("Trade not null");
                trade.setTrade_price(current.getPrice());
                tradeRepository.save(trade);
            } else {
                System.out.println("Trade is null");
            }
            orderRemaining = order.getOrder_size() - order.getNumber_matched();   
            marker++;
            if (marker == compared.size()) {
                break;
            }
        }
    }
    
    private void matchSellOrder(Order order) {
        List<Order> compared = orderRepository.findActiveBuyOrders();
        int orderRemaining = order.getOrder_size()- order.getNumber_matched();
        int marker = 0;
        
        Order current;
        if (compared.size() > 0) {
            System.out.println("Current has something here");
            current = compared.get(marker);
            System.out.println("Order price: " + order.getPrice() + " Current price: " + current.getPrice());
            System.out.println("Orderremaining: " + orderRemaining);
        } else {
            System.out.println("Exit here");
            return;
        }
        
        while (orderRemaining > 0) {  
            current = compared.get(marker);
            Trade trade = matchOrders(current, order);
            if (trade != null) {
                System.out.println("Trade not null");
                trade.setTrade_price(current.getPrice());
                tradeRepository.save(trade);
            } else {
                System.out.println("Trade is null");
            }
            orderRemaining = order.getOrder_size() - order.getNumber_matched();
            marker++;
            if (marker == compared.size()) {
                break;
            }
        }
    }
    
    private Trade matchOrders(Order buy, Order sell) {
        int buyRemaining = buy.getOrder_size() - buy.getNumber_matched();
        int sellRemaining = sell.getOrder_size() - sell.getNumber_matched();
        
        if ((sell.getPrice().compareTo(buy.getPrice()) < 0) || (sell.getPrice().compareTo(buy.getPrice()) == 0)) {
            System.out.println("Order matched");
            buyRemaining = buy.getOrder_size() - buy.getNumber_matched();
            Trade trade = new Trade();
            trade.setBuyorder(buy);
            trade.setSellorder(sell);

            trade.setTrade_price(sell.getPrice());

            if (buyRemaining > sellRemaining) {
                trade.setTrade_size(sellRemaining);
                buyRemaining -= sellRemaining;
                sellRemaining = 0;
                sell.setNumber_matched(sell.getOrder_size());
                sell.setStatus(StatusEnum.FULFILLED);
                buy.setNumber_matched(buy.getOrder_size() - buyRemaining);
            } else {
                trade.setTrade_size(buyRemaining);
                sellRemaining -= buyRemaining;
                buyRemaining = 0;
                buy.setNumber_matched(buy.getOrder_size());
                buy.setStatus(StatusEnum.FULFILLED);
                sell.setNumber_matched(sell.getOrder_size() - sellRemaining);
                if(sell.getNumber_matched() == sell.getOrder_size()) {
                    sell.setStatus(StatusEnum.FULFILLED);
                }
            }

            trade.setTrade_time(LocalDateTime.now(ZoneId.of("GMT")));

            System.out.println("Trade sell order id: " + trade.getSellorder().getId() + " version: " + trade.getSellorder().getId().getVersion() + ""
            + "Trade buy order id: " + trade.getBuyorder().getId() + " version: " + trade.getBuyorder().getId().getVersion());

            System.out.println("Sell order id: " + sell.getId() + " version: " + sell.getId().getVersion() + ""
            + "Buy order id: " + buy.getId() + " version: " + buy.getId().getVersion());

            if (buy.getStatus() == StatusEnum.PENDING)
                buy.setStatus(StatusEnum.ACTIVE);
            
            if (sell.getStatus() == StatusEnum.PENDING)
                sell.setStatus(StatusEnum.ACTIVE);
            
            orderRepository.save(sell);
            orderRepository.save(buy);
            
            return trade;
        } else {
            return null;
        }
        
    }
    
}
