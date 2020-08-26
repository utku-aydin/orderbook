package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.Order;
import java.util.List;
import java.util.Map;

public interface OrderService {
    
    List<Order> getOrders();
    List<Order> getBuyOrders();
    List<Order> getSellOrders();
    List<Order> getActiveBuyOrders();
    List<Order> getActiveSellOrders();
    List<Order> getOrderHistory(int id);
    Order addOrder(Map<String, String> orderData);
    Order updateOrder(Map<String, String> orderData);
    Order cancelOrderByID(int id, int version);
    
}
