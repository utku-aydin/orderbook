package com.mthree.orderbook.repository;

import com.mthree.orderbook.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer> {

    /**
     * Fetches all orders from the database and orders them
     * by their time of occurrence descending
     * @return a list of trades
     */
    @Query(value = "SELECT * FROM `ob_trade` ORDER BY trade_time DESC", nativeQuery = true)
    List<Trade> findTradesByDate();

    /**
     * Fetches a specified number of trades based on their
     * time of occurrence
     * @param count the number of trades to be fetched
     * @return a list of trades
     */
    @Query(value = "SELECT * FROM `ob_trade` ORDER BY trade_time DESC LIMIT ?1", nativeQuery = true)
    List<Trade> getCountTrades(int count);
    
}
