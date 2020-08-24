package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.Trade;
import com.mthree.orderbook.repository.TradeRepository;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TradeServiceDB implements TradeService {

    private final TradeRepository tradeRepository;

    public TradeServiceDB(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @Override
    public BigDecimal[] getPricesWithStep(int days) {
        LocalDateTime neededDate = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0).minusDays(days);

        switch(days) {
            //return one price per hour
            case 1:
                BigDecimal[] prices = new BigDecimal[8];
                int neededHour = 9;
                while(neededHour < 17) {
                    for (Trade t : tradeRepository.findTradesFromDate(neededDate)) {
                        if (neededHour > 17) {
                            break;
                        }
                        if (t.getTrade_time().getHour() == neededHour) {
                            prices[neededHour - 9] = t.getTrade_price();
                            neededHour++;
                        }
                    }
                    neededHour++;
                }
            //return one price at around 9 and one around 5 for each day
            //excluding weekend
            case 7:
               prices = new BigDecimal[10];
               int totalFound = 0;

               while(totalFound < 10) {
                   for(Trade t : tradeRepository.findTradesFromDate(neededDate)) {
                       if(t.getTrade_time().getDayOfWeek().equals(DayOfWeek.SATURDAY) || t.getTrade_time().getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                           continue;
                       }
                       if(totalFound == 10) {
                           break;
                       }
                       
                   }
               }
            //return one price per day
            //excluding weekends
            case 30:

        }

        return null;
    }
    
    
}
