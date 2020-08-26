package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.Stock;
import java.math.BigDecimal;
import java.util.List;

public interface StockService {

    /**
     * Get the change in price for a stock from the two most recent trades
     * @param stock_id the id of the desired stock
     * @return BigDecimal representation of the difference in price
     */

    BigDecimal getChangeForStock(int stock_id);

    /**
     * Get the change in price for all stocks
     * @return a list of strings representing the stock and the change
     */
    List<String> getAllStocksWithChange();

    /**
     * Fetch all stocks from the database
     * @return a list of all stocks
     */
    List<Stock> getAllStocks();
    
}
