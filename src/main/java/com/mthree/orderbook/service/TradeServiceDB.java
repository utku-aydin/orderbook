package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.Trade;
import com.mthree.orderbook.repository.TradeRepository;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;

public class TradeServiceDB implements TradeService {

    private final TradeRepository tradeRepository;

    public TradeServiceDB(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @Override
    public List<BigDecimal> getPricesWithStep(int interval, int count) {
        List<Trade> trades = tradeRepository.findAll(Sort.by(Sort.Direction.DESC, "trade_time"));
        List<BigDecimal> prices = new ArrayList<>();
        prices.add(trades.get(0).getTrade_price());
        int checked = 1;
        while(checked < count) {
            //if all trades have been checked, stop and return
            if(checked >= trades.size()) {
                break;
            }
            Trade t = trades.get(checked++);
            //if there's a trade at that time, add its price
            if(LocalDateTime.now().minusSeconds(interval*checked).truncatedTo(ChronoUnit.SECONDS)
                    .isEqual(t.getTrade_time().truncatedTo(ChronoUnit.SECONDS))) {
                prices.add(t.getTrade_price());
            }
            //otherwise, add the price of the previous trade
            else {
                prices.add(prices.get(prices.size()-1));
            }
        }
        //if there is a need, add zeroes to match the needed size
        while(prices.size() < count) {
            prices.add(new BigDecimal("0"));
        }
        return prices;
    }
    
}
