package com.mthree.orderbook.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ob_stock")
public class Stock {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    @Id    
    private int id;
    
    @Column(name = "tick_size")
    private String tick_size;
    
    @Column(name = "stock_symbol")
    private String stock_symbol;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTick_size() {
        return tick_size;
    }

    public void setTick_size(String tick_size) {
        this.tick_size = tick_size;
    }

    public String getStock_symbol() {
        return stock_symbol;
    }

    public void setStock_symbol(String stock_symbol) {
        this.stock_symbol = stock_symbol;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.id;
        hash = 17 * hash + Objects.hashCode(this.tick_size);
        hash = 17 * hash + Objects.hashCode(this.stock_symbol);
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
        final Stock other = (Stock) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.tick_size, other.tick_size)) {
            return false;
        }
        if (!Objects.equals(this.stock_symbol, other.stock_symbol)) {
            return false;
        }
        return true;
    }
    
}
