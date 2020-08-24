package com.mthree.orderbook.repository;

import com.mthree.orderbook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
