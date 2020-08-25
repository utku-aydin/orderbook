package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.Trade;

import java.math.BigDecimal;
import java.util.List;

public interface TradeService {

    public List<BigDecimal> getPricesWithStep(int interval, int count);
    public List<Trade> getCountTrades(int count);
    
}
