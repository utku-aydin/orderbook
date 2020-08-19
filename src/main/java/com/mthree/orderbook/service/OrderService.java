/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.Order;
import java.util.List;
import java.util.Map;

/**
 *
 * @author utkua
 */
public interface OrderService {
    
    public List<Order> getOrders();
    public List<Order> getBuyOrders();
    public List<Order> getSellOrders();
    public Order getOrderByID(int id);
    public Order addOrder(Map<String, String> orderData);
    public Order updateOrder(Map<String, String> orderData);
    public Order cancelOrderByID(int id);
    
}
