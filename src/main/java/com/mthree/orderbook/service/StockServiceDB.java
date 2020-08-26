package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.Stock;
import com.mthree.orderbook.repository.StockRepository;
import java.math.BigDecimal;
import java.util.LinkedList;
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
            change = changes.get(0).subtract(changes.get(2));
            System.out.println("0: " + changes.get(0) + " 2: " + changes.get(2));
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return null;
        }

        return change;
    }
    
    @Override
    public List<String> getAllStocksWithChange() {
        List<Stock> stocks = stockRepository.findAll();
        List<String> stocksWithChange = new LinkedList<>();
        for (Stock stock: stocks) {
            String stockWithChange = stock.getStock_symbol() + ": " + getMostRecentPriceForStock(stock.getId()) + "(" + getChangeForStock(stock.getId()) + ")";
            stocksWithChange.add(stockWithChange);
        }
        
        return stocksWithChange;
    }
    
    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }
    
    private BigDecimal getMostRecentPriceForStock(int stock_id) {
        List<BigDecimal> changes = stockRepository.findMostRecentTrade(stock_id);
        BigDecimal change;
        try {
            change = changes.get(0);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return null;
        }

        return change;
    }
    
}
