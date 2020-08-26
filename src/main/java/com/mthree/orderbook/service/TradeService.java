package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.Trade;

import java.math.BigDecimal;
import java.util.List;

public interface TradeService {

    /**
     * Extracts the different price points at different times.
     * Goes back by the interval and fetches the price from that point
     * until the number of prices fetched matches the 'count' parameter
     * @param interval the interval for prices to be fetched
     * @param count the number of prices desired
     * @return a list with prices
     */
    List<BigDecimal> getPricesWithStep(int interval, int count);
    
    /**
     * Extracts the different price points.
     * Fetches the price from from each most recent trade
     * until the number of prices fetched matches the 'count' parameter
     * @param count the number of prices desired
     * @return a list with prices
     */
    List<BigDecimal> getPricesWithCount(int count);

    /**
     * Fetches a number of trades specified by the user
     * in descending order based on their time of occurrence
     * @param count the number of trades desired
     * @return a list of trades
     */
    List<Trade> getCountTrades(int count);
    
}
