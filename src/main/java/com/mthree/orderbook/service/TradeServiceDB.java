package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.Trade;
import com.mthree.orderbook.repository.TradeRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TradeServiceDB implements TradeService {

    private final TradeRepository tradeRepository;

    public TradeServiceDB(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @Override
    public List<BigDecimal> getPricesWithStep(long days) {
        LocalDateTime neededDate = LocalDateTime.now().minusDays(days);

        List<BigDecimal> prices = new ArrayList<>();

        tradeRepository.findTradesFromDate(neededDate)
                .stream()
                .forEach(t -> prices.add(t.getTrade_price()));

        return prices;
    }
}
