/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.orderbook.repository;

import com.mthree.orderbook.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author utkua
 */
@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer> {
    
}
