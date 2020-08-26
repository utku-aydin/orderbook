package com.mthree.orderbook.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ob_trade")
public class Trade implements Serializable {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    @Id    
    private int id;
    
    @ManyToOne
    @JoinColumns({
        @JoinColumn(
                name = "buy_id", 
                referencedColumnName = "id",
                nullable = false),
        @JoinColumn(
                name = "buy_version", 
                referencedColumnName = "version",
                nullable = false)
    })
    private Order buyorder;
    
    @ManyToOne
    @JoinColumns({
        @JoinColumn(
                name = "sell_id", 
                referencedColumnName = "id",
                nullable = false),
        @JoinColumn(
                name = "sell_version", 
                referencedColumnName = "version",
                nullable = false)
    })
    private Order sellorder;
    
    @Column(nullable = false)
    private LocalDateTime trade_time;
    
    @Column(nullable = false)
    private BigDecimal trade_price;
    
    @Column(nullable = false)
    private int trade_size;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getBuyorder() {
        return buyorder;
    }

    public void setBuyorder(Order buyorder) {
        this.buyorder = buyorder;
    }

    public Order getSellorder() {
        return sellorder;
    }

    public void setSellorder(Order sellorder) {
        this.sellorder = sellorder;
    }

    public LocalDateTime getTrade_time() {
        return trade_time;
    }

    public void setTrade_time(LocalDateTime trade_time) {
        this.trade_time = trade_time;
    }

    public BigDecimal getTrade_price() {
        return trade_price;
    }

    public void setTrade_price(BigDecimal trade_price) {
        this.trade_price = trade_price;
    }

    public int getTrade_size() {
        return trade_size;
    }

    public void setTrade_size(int trade_size) {
        this.trade_size = trade_size;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.id;
        hash = 31 * hash + Objects.hashCode(this.buyorder);
        hash = 31 * hash + Objects.hashCode(this.sellorder);
        hash = 31 * hash + Objects.hashCode(this.trade_time);
        hash = 31 * hash + Objects.hashCode(this.trade_price);
        hash = 31 * hash + this.trade_size;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Trade other = (Trade) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.trade_size != other.trade_size) {
            return false;
        }
        if (!Objects.equals(this.buyorder, other.buyorder)) {
            return false;
        }
        if (!Objects.equals(this.sellorder, other.sellorder)) {
            return false;
        }
        if (!Objects.equals(this.trade_time, other.trade_time)) {
            return false;
        }
        if (!Objects.equals(this.trade_price, other.trade_price)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Trade{" + "id=" + id + ", buyorder=" + buyorder + ", sellorder=" + sellorder + ", trade_time=" + trade_time + ", trade_price=" + trade_price + ", trade_size=" + trade_size + '}';
    }
    
}
