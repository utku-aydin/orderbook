package com.mthree.orderbook.repository;

import com.mthree.orderbook.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer> {

    @Query(value = "SELECT * FROM `ob_trade` ORDER BY trade_time DESC", nativeQuery = true)
    List<Trade> findTradesByDate();
    
    @Query(value = "SELECT * FROM `ob_trade` ORDER BY trade_time DESC LIMIT ?1", nativeQuery = true)
    List<Trade> getCountTrades(int count);
    
}
