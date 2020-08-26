package com.mthree.orderbook.repository;

import com.mthree.orderbook.entity.Stock;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StockRepository extends JpaRepository<Stock, Integer> {

    /**
     * Finds the two most recent trades for a stock
     * @param stock_id the stock's id
     * @return a list containing the trade prices for the trades
     */
    @Query(value = "SELECT trade_price FROM `ob_trade` obt JOIN `ob_order` obo ON obt.buy_id = obo.id JOIN ob_stock obs ON "
            + "obo.stock_id = obs.id WHERE obs.id = ?1 ORDER BY obt.trade_time DESC LIMIT 4", nativeQuery = true)
    List<BigDecimal> findMostRecentTrades(int stock_id);
    
    @Query(value = "SELECT trade_price FROM `ob_trade` obt JOIN `ob_order` obo ON obt.buy_id = obo.id JOIN ob_stock obs ON "
            + "obo.stock_id = obs.id WHERE obs.id = ?1 ORDER BY obt.trade_time DESC LIMIT 1", nativeQuery = true)
    List<BigDecimal> findMostRecentTrade(int stock_id);
    
}
