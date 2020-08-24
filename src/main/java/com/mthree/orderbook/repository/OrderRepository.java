/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.orderbook.repository;

import com.mthree.orderbook.entity.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author utkua
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT * FROM `ob_order` WHERE side = \"buy\" ORDER BY \"price\"", nativeQuery = true)
    List<Order> findBuyOrders();
    @Query(value = "SELECT * FROM `ob_order` WHERE side = \"sell\" ORDER BY \"price\" ASC", nativeQuery = true)
    List<Order> findSellOrders();
    @Query(value = "SELECT * FROM `ob_order` WHERE side = \"buy\" AND status = \"ACTIVE\" ORDER BY \"price\", \"placedAt\"", nativeQuery = true)
    List<Order> findActiveBuyOrders();
    @Query(value = "SELECT * FROM `ob_order` WHERE side = \"sell\" AND status = \"ACTIVE\" ORDER BY \"price\" ASC, \"placedAt\"", nativeQuery = true)
    List<Order> findActiveSellOrders();
    @Query(value = "SELECT * FROM `ob_order` ORDER BY id DESC", nativeQuery = true)
    List<Order> findHighestId();
  
}
