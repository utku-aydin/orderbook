package com.mthree.orderbook.repository;

import com.mthree.orderbook.entity.Order;
import com.mthree.orderbook.entity.OrderId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, OrderId> {

    @Query(value = "SELECT * FROM `ob_order` WHERE side = \"BUY\" ORDER BY \"price\"", nativeQuery = true)
    List<Order> findBuyOrders();
    
    @Query(value = "SELECT * FROM `ob_order` WHERE side = \"SELL\" ORDER BY \"price\" ASC", nativeQuery = true)
    List<Order> findSellOrders();

    @Query(value = "SELECT * FROM ob_order o \n" +
            "WHERE o.version = (\n" +
            "            SELECT max(version)\n" +
            "            FROM ob_order o2\n" +
            "            WHERE o.id = o2.id\n" +
            "            )\n" +
            "        && o.status = 'ACTIVE'\n" +
            "        && o.side = 'BUY'\n" +
            "ORDER BY cast(price as DECIMAL(10,2)) ASC, placed_at ASC", nativeQuery = true)
    List<Order> findActiveBuyOrders();
    
    @Query(value = "SELECT * FROM ob_order o\n" +
            "WHERE o.version = (\n" +
            "            SELECT max(version)\n" +
            "            FROM ob_order o2\n" +
            "            WHERE o.id = o2.id\n" +
            "            )\n" +
            "        && o.status = 'ACTIVE'\n" +
            "        && o.side = 'SELL'" +
            "ORDER BY cast(price as DECIMAL(10,2)) ASC, placed_at ASC", nativeQuery = true)
    List<Order> findActiveSellOrders();

    /**
     * Finds the order with the highest id in the database
     * @return a list with all Order object ordered by id descending
     */
    @Query(value = "SELECT * FROM `ob_order` ORDER BY id DESC", nativeQuery = true)
    List<Order> findHighestId();
    
    @Query(value = "SELECT * FROM `ob_order` WHERE id = ?1", nativeQuery = true)
    List<Order> getOrderHistory(int id);
  
}
