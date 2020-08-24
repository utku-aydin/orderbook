/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.orderbook.controller;

import com.mthree.orderbook.entity.Order;
import com.mthree.orderbook.service.OrderService;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author utkua
 */
@RestController
@CrossOrigin(origins={"http://localhost:8080", "http://localhost:3000", "null"})
@RequestMapping("/api")
public class OrderController {
    
    private final OrderService orderService;

    public OrderController(OrderService service) {
        this.orderService = service;
    }
    
    @GetMapping("/buyOrders")
    public ResponseEntity<List<Order>> getBuyOrders() {
        List<Order> orders = orderService.getActiveBuyOrders();
        if (orders.isEmpty()) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        
        return ResponseEntity.ok(orders);
    }
    
    @GetMapping("/sellOrders")
    public ResponseEntity<List<Order>> getSellOrders() {
        List<Order> orders = orderService.getActiveSellOrders();
        if (orders.isEmpty()) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        
        return ResponseEntity.ok(orders);
    }
    
    @PostMapping("/order")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Order> addOrder(@RequestBody Map<String, String> orderData) {
        Order added = orderService.addOrder(orderData);
        return ResponseEntity.ok(added);
    }
    
    @PutMapping("/order")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Order> updateOrder(@RequestBody Map<String, String> orderData) {
        Order added = orderService.updateOrder(orderData);
        return ResponseEntity.ok(added);
    }
    
    @DeleteMapping("/order")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Order> cancelOrder(@RequestBody Integer id) {
        Order added = orderService.cancelOrderByID(id);
        return ResponseEntity.ok(added);
    }
  
}
