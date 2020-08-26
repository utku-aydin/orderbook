package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.Stock;
import java.math.BigDecimal;
import java.util.List;

public interface StockService {
    
    BigDecimal getChangeForStock(int stock_id);
    List<Stock> getAllStocks();
    
}
