/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.orderbook.repository;

import com.mthree.orderbook.entity.OB_Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author utkua
 */
@Repository
public interface OrderRepository extends JpaRepository<OB_Order, Integer> {
    
    @Query(value = "SELECT * FROM `ob_order` WHERE side = \"buy\" ORDER BY \"price\"", nativeQuery = true)
    List<OB_Order> findBuyOrders();
    
    @Query(value = "SELECT * FROM `ob_order` WHERE side = \"sell\" ORDER BY \"price\" ASC", nativeQuery = true)
    List<OB_Order> findSellOrders();
    
    @Query(value = "SELECT * FROM `ob_order` ORDER BY \"placedat\" DESC LIMIT 1", nativeQuery = true)
    OB_Order findMostRecentOrder();
  
}
