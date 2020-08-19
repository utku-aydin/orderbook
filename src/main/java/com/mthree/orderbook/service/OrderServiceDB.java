/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.Order;
import com.mthree.orderbook.repository.OrderRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    
    public OrderServiceDB (OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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
    public Order getOrderByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order addOrder(Map<String, String> orderData) {
        Order order = new Order();
        
        order.setSymbol(orderData.get("symbol"));
        order.setPrice(new BigDecimal(orderData.get("price")));
        order.setSize(Integer.parseInt(orderData.get("size")));
        order.setSide(orderData.get("side"));
        order.setNumbermatched(Integer.parseInt(orderData.get("numbermatched")));
        order.setPlacedat(LocalDateTime.parse(orderData.get("placedat")));
        order.setFulfilled(Boolean.parseBoolean(orderData.get("fulfilled")));
        
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order updateOrder(Map<String, String> orderData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order cancelOrderByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
