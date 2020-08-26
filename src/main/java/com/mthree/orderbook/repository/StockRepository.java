package com.mthree.orderbook.repository;

import com.mthree.orderbook.entity.Stock;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StockRepository extends JpaRepository<Stock, Integer> {
    
    @Query(value = "SELECT trade_price FROM `ob_trade` obt JOIN `ob_order` obo ON obt.buy_id = obo.id JOIN ob_stock obs ON "
            + "obo.stock_id = obs.id WHERE obs.id = ?1 ORDER BY obt.trade_time DESC LIMIT 2", nativeQuery = true)
    List<BigDecimal> findMostRecentTrades(int stock_id);
    
}
