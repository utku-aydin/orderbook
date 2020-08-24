package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.Trade;

import java.math.BigDecimal;
import java.util.List;

public interface TradeService {

    BigDecimal[] getPricesWithStep(int days);
}
