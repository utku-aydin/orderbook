package com.mthree.orderbook.repository;

import com.mthree.orderbook.entity.OrderAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderAuditRepository extends JpaRepository<OrderAudit, Integer> {

}
