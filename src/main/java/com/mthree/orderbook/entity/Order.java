/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.orderbook.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author utkua
 */
@Entity
public class Order {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id    
    private int id;
    
    @Column(nullable = false)
    private String symbol;
    
    @Column(nullable = false)
    private BigDecimal price;
    
    @Column(nullable = false)
    private int size;

    @Column(nullable = false)
    private int sold;
    
    @Column(nullable = false)
    private String side;
    
    @Column(nullable = false)
    private LocalDateTime placedAt;
    
    @Column(nullable = false)
    private boolean fullFilled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public LocalDateTime getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(LocalDateTime placedAt) {
        this.placedAt = placedAt;
    }

    public boolean isFullFilled() {
        return fullFilled;
    }

    public void setFullFilled(boolean fullFilled) {
        this.fullFilled = fullFilled;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.id;
        hash = 31 * hash + Objects.hashCode(this.symbol);
        hash = 31 * hash + Objects.hashCode(this.price);
        hash = 31 * hash + this.size;
        hash = 31 * hash + this.sold;
        hash = 31 * hash + Objects.hashCode(this.side);
        hash = 31 * hash + Objects.hashCode(this.placedAt);
        hash = 31 * hash + (this.fullFilled ? 1 : 0);
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
        final Order other = (Order) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.size != other.size) {
            return false;
        }
        if (this.sold != other.sold) {
            return false;
        }
        if (this.fullFilled != other.fullFilled) {
            return false;
        }
        if (!Objects.equals(this.symbol, other.symbol)) {
            return false;
        }
        if (!Objects.equals(this.side, other.side)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (!Objects.equals(this.placedAt, other.placedAt)) {
            return false;
        }
        return true;
    }
    
}
