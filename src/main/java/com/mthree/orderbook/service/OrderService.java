/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.OB_Order;
import java.util.List;
import java.util.Map;

/**
 *
 * @author utkua
 */
public interface OrderService {
    
    public List<OB_Order> getOrders();
    public List<OB_Order> getBuyOrders();
    public List<OB_Order> getSellOrders();
    public List<OB_Order> getActiveBuyOrders();
    public List<OB_Order> getActiveSellOrders();
    public OB_Order getOrderByID(int id);
    public OB_Order addOrder(Map<String, String> orderData);
    public OB_Order updateOrder(Map<String, String> orderData);
    public OB_Order cancelOrderByID(int id);
    
}
