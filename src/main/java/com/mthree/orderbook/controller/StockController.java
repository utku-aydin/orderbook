package com.mthree.orderbook.controller;

import com.mthree.orderbook.entity.Stock;

import java.math.BigDecimal;
import java.util.List;

import com.mthree.orderbook.service.StockService;
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
public class StockController {
    
    private final StockService stockService;
    
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }
    
    @GetMapping("/ticker/{id}")
    public ResponseEntity<BigDecimal> getStockTickers(@PathVariable Integer id) {
        BigDecimal change = stockService.getChangeForStock(id);
        if (change == null) {
            // Not enough trades for stock
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        
        return ResponseEntity.ok(change);
    }
    
    @GetMapping("/tickers")
    public ResponseEntity<List<String>> getAllStockTickers() {
        List<String> changes = stockService.getAllStocksWithChange();
        if (changes == null) {
            // Not enough trades for stock
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        
        return ResponseEntity.ok(changes);
    }
    
    @GetMapping("/stocks")
    public ResponseEntity<List<Stock>> getStocks() {
        List<Stock> stocks = stockService.getAllStocks();
        if (stocks.isEmpty()) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        
        return ResponseEntity.ok(stocks);
    }
    
}
