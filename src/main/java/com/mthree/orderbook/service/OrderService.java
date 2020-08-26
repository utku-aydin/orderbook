package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.Order;
import java.util.List;
import java.util.Map;

public interface OrderService {

    /**
     * Fetches all orders from the database
     * @return a list of all orders
     */
    List<Order> getOrders();

    /**
     * Fetches all BUY orders from the database
     * @return a list of all buy orders
     */
    List<Order> getBuyOrders();

    /**
     * Fetches all SELL orders from the database
     * @return a list of all sell orders
     */
    List<Order> getSellOrders();

    /**
     * Fetches all BUY orders that are ACTIVE from the database
     * @return a list of ACTIVE BUY orders
     */
    List<Order> getActiveBuyOrders();

    /**
     * Fetches all SELL orders that are ACTIVE from the database
     * @return a list of ACTIVE SELL orders
     */
    List<Order> getActiveSellOrders();

    /**
     * Fetches all versions of an order
     * @param id the id of the desired order
     * @return a list of Order object representing the order's states
     */
    List<Order> getOrderHistory(int id);

    /**
     * Adds an order to the database
     * @param orderData JSON representation of an order to be added
     *                  which should be in the following format:
     *                  {
     *                       "price": "?",
     *                       "order_size": "?",
     *                       "number_matched": "?",
     *                       "side": "SELL/BUY",
     *                       "status": "PENDING/ACTIVE/FULFILLED/CANCELLED",
     *                       "usr_id": ?,
     *                       "stock_id": ?
     *                  }
     * @return the added order
     */
    Order addOrder(Map<String, String> orderData);

    /**
     * Updates an order's details in the database
     * @param orderData JSON representation of the order which is going to take place
     *                  of the old one in the database.
     *                  It should be in the following format:
     *                  {
     *                       "price": "?",
     *                       "order_size": "?",
     *                       "number_matched": "?",
     *                       "side": "SELL/BUY",
     *                       "status": "PENDING/ACTIVE/FULFILLED/CANCELLED",
     *                       "usr_id": ?,
     *                       "stock_id": ?
     *                  }
     * @return the added order
     */
    Order updateOrder(Map<String, String> orderData);

    /**
     * Set the status of an order to CANCELLED
     * @param id the id of the order
     * @param version the current version of the order
     * @return the cancelled Order object
     */
    Order cancelOrderByID(int id, int version);
    
}
