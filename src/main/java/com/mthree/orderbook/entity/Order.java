/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.orderbook.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    
    @JsonProperty("symbol")
    @Column(nullable = false)
    private String symbol;
    
    @JsonProperty("price")
    @Column(nullable = false)
    private BigDecimal price;
    
    @JsonProperty("size")
    @Column(nullable = false)
    private int size;

    @JsonProperty("numbermatched")
    @Column(nullable = false)
    private int numbermatched;
    
    @JsonProperty("side")
    @Column(nullable = false)
    private String side;
    
    @JsonProperty("placedat")
    @Column(nullable = false)
    private LocalDateTime placedat = LocalDateTime.now();
    
    @JsonProperty("fulfilled")
    @Column(nullable = false)
    private boolean fulfilled;

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

    public int getNumbermatched() {
        return numbermatched;
    }

    public void setNumbermatched(int sold) {
        this.numbermatched = sold;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public LocalDateTime getPlacedat() {
        return placedat;
    }

    public void setPlacedat(LocalDateTime placedAt) {
        this.placedat = placedAt;
    }

    public boolean isFulfilled() {
        return fulfilled;
    }

    public void setFulfilled(boolean fulfilled) {
        this.fulfilled = fulfilled;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.id;
        hash = 31 * hash + Objects.hashCode(this.symbol);
        hash = 31 * hash + Objects.hashCode(this.price);
        hash = 31 * hash + this.size;
        hash = 31 * hash + this.numbermatched;
        hash = 31 * hash + Objects.hashCode(this.side);
        hash = 31 * hash + Objects.hashCode(this.placedat);
        hash = 31 * hash + (this.fulfilled ? 1 : 0);
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
        if (this.numbermatched != other.numbermatched) {
            return false;
        }
        if (this.fulfilled != other.fulfilled) {
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
        if (!Objects.equals(this.placedat, other.placedat)) {
            return false;
        }
        return true;
    }
    
}
