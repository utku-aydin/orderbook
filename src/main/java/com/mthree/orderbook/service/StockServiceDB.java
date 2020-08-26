package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.Stock;
import com.mthree.orderbook.repository.StockRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Repository;

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
            return null;
        }

        return change;
    }
    
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }
    
}
