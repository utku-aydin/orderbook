/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.orderbook.service;

import com.mthree.orderbook.entity.OB_Order;
import com.mthree.orderbook.entity.OB_OrderId;
import com.mthree.orderbook.entity.SideEnum;
import com.mthree.orderbook.entity.StatusEnum;
import com.mthree.orderbook.entity.Trade;
import com.mthree.orderbook.repository.OrderRepository;
import com.mthree.orderbook.repository.TradeRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Repository;

/**
 *
 * @author utkua
 */
@Repository
public class OrderServiceDB implements OrderService {
    
    private final OrderRepository orderRepository;
    private final TradeRepository tradeRepository;
    
    public OrderServiceDB (OrderRepository orderRepository, TradeRepository tradeRepository) {
        this.orderRepository = orderRepository;
        this.tradeRepository = tradeRepository;
    }

    @Override
    public List<OB_Order> getOrders() {
        return orderRepository.findAll();
    }
    
    @Override
    public List<OB_Order> getBuyOrders() {
        return orderRepository.findBuyOrders();
    }

    @Override
    public List<OB_Order> getSellOrders() {
        return orderRepository.findSellOrders();
    }

    @Override
    public OB_Order getOrderByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OB_Order addOrder(Map<String, String> orderData) {
        OB_Order order = new OB_Order();
        
        Set<String> keys = orderData.keySet();
        Collection<String> values = orderData.values();
        for (int i = 0; i < orderData.size(); i++) {
            System.out.println();
        }
        
        order.setSymbol(orderData.get("symbol"));
        order.setPrice(new BigDecimal(orderData.get("price")));
        order.setOrdersize(Integer.parseInt(orderData.get("ordersize")));
        order.setSide(SideEnum.valueOf(orderData.get("side")));
        order.setNumbermatched(Integer.parseInt(orderData.get("numbermatched")));
        order.setPlacedat(LocalDateTime.now());
        order.setStatus(StatusEnum.valueOf(orderData.get("status")));
        order.setUsersymbol(orderData.get("userSymbol"));
        OB_OrderId id = new OB_OrderId();
        id.setVersion(0);
        if (orderRepository.findHighestId().isEmpty()) {
            System.out.println("Nully");
            id.setId(1);
        } else {
            id.setId(orderRepository.findHighestId().get(0).getId().getId() + 1);
        }
        order.setId(id);
        //order.getId().setVersion(0);
        
        order = orderRepository.saveAndFlush(order);
        //order.setId(orderRepository.findMostRecentOrder().get(0).getId());
        //order.setId(10);
        System.out.println("Order id set: " + order.getId());
        
        if (order.getSide() == SideEnum.BUY) {
            matchBuyOrder(order);
        } else {
            matchSellOrder(order);
        }
        
        return order;
    }

    @Override
    public OB_Order updateOrder(Map<String, String> orderData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OB_Order cancelOrderByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void matchBuyOrder(OB_Order order) {
        List<OB_Order> compared = orderRepository.findActiveSellOrders();
        int orderRemaining = order.getOrdersize()- order.getNumbermatched();
        int marker = 0;
        
        OB_Order current;
        if (compared.size() > 0) {
            System.out.println("Current has something here");
            current = compared.get(marker);
        } else {
            System.out.println("Exit here");
            return;
        }
        
        while (orderRemaining > 0) {
            current = compared.get(marker);
            /*if ((order.getPrice().compareTo(current.getPrice()) > 0) || (order.getPrice().compareTo(current.getPrice()) == 0)) {
                System.out.println("Order matched");
                int currentRemaining = current.getOrdersize() - current.getNumbermatched();
                Trade trade = new Trade();
                trade.setSellorder(current);
                trade.setBuyorder(order);
                
                trade.setTradeprice(order.getPrice());
                
                if (currentRemaining > orderRemaining) {
                    trade.setTradesize(orderRemaining);
                    currentRemaining -= orderRemaining;
                    orderRemaining = 0;
                    order.setNumbermatched(order.getOrdersize());
                    order.setStatus(StatusEnum.FULFILLED);
                    current.setNumbermatched(current.getOrdersize() - currentRemaining);
                } else {
                    trade.setTradesize(currentRemaining);
                    orderRemaining -= currentRemaining;
                    currentRemaining = 0;
                    current.setNumbermatched(current.getOrdersize());
                    current.setStatus(StatusEnum.FULFILLED);
                    order.setNumbermatched(order.getOrdersize() - orderRemaining);
                    if(order.getNumbermatched() == order.getOrdersize()) {
                        order.setStatus(StatusEnum.FULFILLED);
                    }
                }
                
                trade.setTradetime(LocalDateTime.now());
                
                tradeRepository.save(trade);
                orderRepository.save(order);
                orderRepository.save(current);
                marker++;
                if (marker == compared.size()) {
                    break;
                }
            } else {
                break;
            }*/            Trade trade = matchOrders(current, order);
            if (trade != null) {
                System.out.println("Trade not null");
                trade.setTradeprice(current.getPrice());
                tradeRepository.save(trade);
            } else {
                System.out.println("Trade is null");
            }
            marker++;
            if (marker == compared.size()) {
                break;
            }
        }
    }
    
    private void matchSellOrder(OB_Order order) {
        List<OB_Order> compared = orderRepository.findActiveBuyOrders();
        int orderRemaining = order.getOrdersize()- order.getNumbermatched();
        int marker = 0;
        
        OB_Order current;
        if (compared.size() > 0) {
            System.out.println("Current has something here");
            current = compared.get(marker);
            System.out.println("Order price: " + order.getPrice() + " Current price: " + current.getPrice());
            System.out.println("Orderremaining: " + orderRemaining);
        } else {
            System.out.println("Exit here");
            return;
        }
        
        while (orderRemaining > 0) {      
            current = compared.get(marker);
            /*if ((order.getPrice().compareTo(current.getPrice()) < 0) || (order.getPrice().compareTo(current.getPrice()) == 0)) {
                System.out.println("Order matched");
                int currentRemaining = current.getOrdersize() - current.getNumbermatched();
                Trade trade = new Trade();
                trade.setBuyorder(current);
                trade.setSellorder(order);
                
                trade.setTradeprice(order.getPrice());
                
                if (currentRemaining > orderRemaining) {
                    trade.setTradesize(orderRemaining);
                    currentRemaining -= orderRemaining;
                    orderRemaining = 0;
                    order.setNumbermatched(order.getOrdersize());
                    order.setStatus(StatusEnum.FULFILLED);
                    current.setNumbermatched(current.getOrdersize() - currentRemaining);
                } else {
                    trade.setTradesize(currentRemaining);
                    orderRemaining -= currentRemaining;
                    currentRemaining = 0;
                    current.setNumbermatched(current.getOrdersize());
                    current.setStatus(StatusEnum.FULFILLED);
                    order.setNumbermatched(order.getOrdersize() - orderRemaining);
                    if(order.getNumbermatched() == order.getOrdersize()) {
                        order.setStatus(StatusEnum.FULFILLED);
                    }
                }
                
                trade.setTradetime(LocalDateTime.now());
                
                System.out.println("Trade sell order id: " + trade.getSellorder().getId() + " version: " + trade.getSellorder().getId().getVersion() + ""
                + "Trade buy order id: " + trade.getBuyorder().getId() + " version: " + trade.getBuyorder().getId().getVersion());

                System.out.println("Sell order id: " + order.getId() + " version: " + order.getId().getVersion() + ""
                + "Buy order id: " + current.getId() + " version: " + current.getId().getVersion());
                
                tradeRepository.save(trade);
                orderRepository.save(order);
                orderRepository.save(current);
                marker++;
                if (marker == compared.size()) {
                    break;
                }
            } else {
                break;
            }*/
            Trade trade = matchOrders(current, order);
            if (trade != null) {
                System.out.println("Trade not null");
                trade.setTradeprice(current.getPrice());
                tradeRepository.save(trade);
            } else {
                System.out.println("Trade is null");
            }
            marker++;
            if (marker == compared.size()) {
                break;
            }
        }
    }
    
    private Trade matchOrders(OB_Order buy, OB_Order sell) {
        int buyRemaining = buy.getOrdersize() - buy.getNumbermatched();
        int sellRemaining = sell.getOrdersize() - sell.getNumbermatched();
        if ((sell.getPrice().compareTo(buy.getPrice()) < 0) || (sell.getPrice().compareTo(buy.getPrice()) == 0)) {
            System.out.println("Order matched");
            buyRemaining = buy.getOrdersize() - buy.getNumbermatched();
            Trade trade = new Trade();
            trade.setBuyorder(buy);
            trade.setSellorder(sell);

            trade.setTradeprice(sell.getPrice());

            if (buyRemaining > sellRemaining) {
                trade.setTradesize(sellRemaining);
                buyRemaining -= sellRemaining;
                sellRemaining = 0;
                sell.setNumbermatched(sell.getOrdersize());
                sell.setStatus(StatusEnum.FULFILLED);
                buy.setNumbermatched(buy.getOrdersize() - buyRemaining);
            } else {
                trade.setTradesize(buyRemaining);
                sellRemaining -= buyRemaining;
                buyRemaining = 0;
                buy.setNumbermatched(buy.getOrdersize());
                buy.setStatus(StatusEnum.FULFILLED);
                sell.setNumbermatched(sell.getOrdersize() - sellRemaining);
                if(sell.getNumbermatched() == sell.getOrdersize()) {
                    sell.setStatus(StatusEnum.FULFILLED);
                }
            }

            trade.setTradetime(LocalDateTime.now());

            System.out.println("Trade sell order id: " + trade.getSellorder().getId() + " version: " + trade.getSellorder().getId().getVersion() + ""
            + "Trade buy order id: " + trade.getBuyorder().getId() + " version: " + trade.getBuyorder().getId().getVersion());

            System.out.println("Sell order id: " + sell.getId() + " version: " + sell.getId().getVersion() + ""
            + "Buy order id: " + buy.getId() + " version: " + buy.getId().getVersion());

            //tradeRepository.save(trade);
            orderRepository.save(sell);
            orderRepository.save(buy);
            
            return trade;
        } else {
            return null;
        }
    }
    
}
