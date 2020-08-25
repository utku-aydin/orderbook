package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.Trade;
import com.mthree.orderbook.repository.TradeRepository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
        Timestamp compTime = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("GMT")).minusSeconds(interval*count));
        String timeStr = "\"" + compTime + "\"";
        List<Trade> trades = tradeRepository.findTradesByDate(compTime)/*findAll(Sort.by(Sort.Direction.DESC, "trade_time"))*/;
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
            //System.out.println("What is now: " + LocalDateTime.now(ZoneId.of("GMT")));
            System.out.println("What is compared: " + LocalDateTime.now(ZoneId.of("GMT")).minusSeconds(interval*checked).truncatedTo(comparison));
            System.out.println("What it is compared to: " + t.getTrade_time().truncatedTo(comparison));
            if(LocalDateTime.now(ZoneId.of("GMT")).minusSeconds(interval*checked).truncatedTo(comparison)
                    .isEqual(t.getTrade_time().truncatedTo(comparison))) {
                prices.add(t.getTrade_price());
            }
            //otherwise, add the price of the previous trade
            else {
                prices.add(prices.get(prices.size() - 1));
            }
        }
        
        //if there is a need, add zeroes to match the needed size
        /*while(prices.size() < count) {
            prices.add(new BigDecimal("0"));
        }

        //if there was no data available at a certain time, set the amount to the previously occurred trade
        while(prices.contains(new BigDecimal("-1"))) {
            int index = prices.indexOf(new BigDecimal("-1"));
            if (index < prices.size() - 1) {
                prices.set(index, prices.get(index+1));
            } else {
                break;
            }
        }
        
        for (int i = prices.size() - 1; i >= 0; i--) {
            if (prices()) {
                
            }
        }*/
        
        return prices;
    }
    
}
