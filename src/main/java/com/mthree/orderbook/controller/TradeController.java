package com.mthree.orderbook.controller;

import com.mthree.orderbook.entity.Trade;
import com.mthree.orderbook.service.TradeService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins={"http://localhost:8080", "http://localhost:3000", "null"})
@RequestMapping("/api")
public class TradeController {
    
    public final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }
    
    @GetMapping("/interval/{interval}/{count}")
    public ResponseEntity<List<BigDecimal>> getTradesIntervalCount(@PathVariable("interval") int interval, @PathVariable("count") int count) {
        List<BigDecimal> prices = tradeService.getPricesWithStep(interval, count);
        if (prices.isEmpty()) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        
        return ResponseEntity.ok(prices);
    }
    
    @GetMapping("/count/{count}")
    public ResponseEntity<List<Trade>> getTradesCount(@PathVariable int count) {
        List<Trade> trades = tradeService.getCountTrades(count);
        if (trades.isEmpty()) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        
        return ResponseEntity.ok(trades);
    }
    
}
