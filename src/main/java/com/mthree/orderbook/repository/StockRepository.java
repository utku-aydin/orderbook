package com.mthree.orderbook.repository;

import com.mthree.orderbook.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Integer> {
}
