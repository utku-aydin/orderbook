package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.Trade;
import com.mthree.orderbook.repository.TradeRepository;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class TradeServiceDB implements TradeService {

    private final TradeRepository tradeRepository;

    public TradeServiceDB(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @Override
    public List<BigDecimal> getPricesWithStep(int interval, int count) {
        List<Trade> trades = tradeRepository.findTradesByDate()/*findAll(Sort.by(Sort.Direction.DESC, "trade_time"))*/;
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
        
        Comparator<Trade> c = new Comparator<Trade>() 
        { 
            @Override
            public int compare(Trade t1, Trade t2) 
            { 
                return t1.getTrade_time().compareTo(t2.getTrade_time()); 
            } 
        }; 

        prices.add(trades.get(0).getTrade_price());
        int checked = 1;
        while(checked < count) {
            //if all trades have been checked, stop and return
            if(checked >= trades.size()) {
                break;
            }
            //Trade comp = new Trade();
            //comp.setTrade_time(LocalDateTime.now(ZoneId.of("GMT")).minusSeconds(interval*checked).truncatedTo(comparison));
            //int index = Collections.binarySearch(prices, comp, c);
            Trade t = trades.get(checked++);
            //if there's a trade at that time, add its price
            //System.out.println("What is now: " + LocalDateTime.now(ZoneId.of("GMT")));
            System.out.println("What is compared: " + LocalDateTime.now(ZoneId.of("GMT")).minusSeconds(interval*checked).truncatedTo(comparison));
            System.out.println("What it is compared to: " + t.getTrade_time().truncatedTo(comparison));
            if(LocalDateTime.now(ZoneId.of("GMT")).minusSeconds(interval*checked).truncatedTo(comparison)
                    .isEqual(t.getTrade_time().truncatedTo(comparison))) {
                prices.add(t.getTrade_price());
            }
            //otherwise, add the price of the previous trade
            else {
                prices.add(new BigDecimal(-1));
            }
        }
        
        //if there is a need, add zeroes to match the needed size

        /*
        //if there was no data available at a certain time, set the amount to the previously occurred trade
        while(prices.contains(new BigDecimal("-1"))) {
            int index = prices.indexOf(new BigDecimal("-1"));
            if (index < prices.size() - 1) {
                prices.set(index, prices.get(index+1));
            } else {
                break;
            }
        }
        */
        
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
