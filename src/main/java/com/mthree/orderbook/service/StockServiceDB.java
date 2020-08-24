/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.orderbook.service;

import com.mthree.orderbook.repository.StockRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author utkua
 */
@Repository
public class StockServiceDB implements StockService {
    
    private final StockRepository stockRepository;

    public StockServiceDB(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }
    
    @Override
    public BigDecimal getChangeForStock(int stock_id) {
        List<BigDecimal> changes = stockRepository.findMostRecentTrades(stock_id);
        BigDecimal change;
        try {
            change = changes.get(0).subtract(changes.get(1));
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
        
        System.out.println("1: " + changes.get(0) + " 2: " + changes.get(1));
        return change;
    }
    
}
