/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.OB_Order;
import com.mthree.orderbook.entity.SideEnum;
import com.mthree.orderbook.repository.OrderRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    public List<OB_Order> getOrders() {
        return orderRepository.findAll();
    }
    
    @Override
    public List<OB_Order> getBuyOrders() {
        return orderRepository.findBuyOrders();
    }

    @Override
    public List<OB_Order> getSellOrders() {
        return orderRepository.findSellOrders();
    }

    @Override
    public OB_Order getOrderByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OB_Order addOrder(Map<String, String> orderData) {
        OB_Order order = new OB_Order();
        
        Set<String> keys = orderData.keySet();
        Collection<String> values = orderData.values();
        for (int i = 0; i < orderData.size(); i++) {
            System.out.println();
        }
        
        order.setSymbol(orderData.get("symbol"));
        order.setPrice(new BigDecimal(orderData.get("price")));
        order.setOrdersize(Integer.parseInt(orderData.get("ordersize")));
        order.setSide(orderData.get("side").equals("buy") ? SideEnum.BUY : SideEnum.SELL);
        order.setNumbermatched(Integer.parseInt(orderData.get("numbermatched")));
        order.setPlacedAt(LocalDateTime.parse(orderData.get("placedat")));
        order.setFulfilled(Boolean.parseBoolean(orderData.get("fulfilled")));
        
        order = orderRepository.save(order);
        
        
        return order;
    }

    @Override
    public OB_Order updateOrder(Map<String, String> orderData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OB_Order cancelOrderByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void matchOrder(OB_Order order) {
        List<OB_Order> compared;
        
        if (order.getSide() == SideEnum.BUY) {
            compared = orderRepository.findSellOrders();
        }
    }
    
}
