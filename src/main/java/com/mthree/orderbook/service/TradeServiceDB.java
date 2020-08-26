package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.Trade;
import com.mthree.orderbook.repository.TradeRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TradeServiceDB implements TradeService {

    private final TradeRepository tradeRepository;

    public TradeServiceDB(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @Override
    public List<BigDecimal> getPricesWithStep(int interval, int count) {
        List<Trade> trades = tradeRepository.findTradesByDate();
        List<BigDecimal> prices = new ArrayList<>();

        if(trades.isEmpty()) {
            return prices;
        }
        
        ChronoUnit comparison;
        if (interval < 59) {
            comparison = ChronoUnit.SECONDS;
        } else if (interval < 3599) {
            comparison = ChronoUnit.MINUTES;
        } else if (interval < 86399) {
            comparison = ChronoUnit.HOURS;
        } else {
            comparison = ChronoUnit.DAYS;
        }


        prices.add(trades.get(0).getTrade_price());
        int checked = 1;
        while(checked < count) {
            //if all trades have been checked, stop and return
            if(checked >= trades.size()) {
                break;
            }
            Trade t = trades.get(checked++);

            //if there's a trade at that time, add its price
            if(LocalDateTime.now(ZoneId.of("GMT")).minusSeconds(interval*checked).truncatedTo(comparison)
                    .isEqual(t.getTrade_time().truncatedTo(comparison))) {
                prices.add(t.getTrade_price());
            }
            //otherwise, set the price to -1
            else {
                prices.add(new BigDecimal(-1));
            }
        }
        
        BigDecimal mOne = new BigDecimal(-1);
        boolean flag = true;
        BigDecimal first = prices.get(0);
        
        for (int i = prices.size() - 1; i >= 1; i--) {
            if (!prices.get(i).equals(mOne)) {
                if (flag) {
                    flag = false;
                    first = prices.get(i);
                }
                if (prices.get(i - 1).equals(mOne)) {
                    prices.set(i - 1, prices.get(i));
                }
            }
        }
        
        for (int i = 0; i < prices.size(); i++) {
            if (prices.get(i).equals(mOne)) {
                prices.set(i, first);
            }
        }
        
        while(prices.size() < count) {
            prices.add(new BigDecimal("0"));
        }
        
        return prices;
    }
    
    @Override
    public List<Trade> getCountTrades(int count) {
        return tradeRepository.getCountTrades(count);
    }
    
}
