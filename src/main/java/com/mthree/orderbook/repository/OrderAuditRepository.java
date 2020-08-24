package com.mthree.orderbook.repository;

import com.mthree.orderbook.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderAuditRepository extends JpaRepository<Order, Integer> {

}
