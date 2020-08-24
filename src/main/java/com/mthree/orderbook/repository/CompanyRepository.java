package com.mthree.orderbook.repository;

import com.mthree.orderbook.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
