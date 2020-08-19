/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 *
 * @author utkua
 */
@Entity
@Table(name = "ob_order")
@IdClass(OB_OrderId.class)
public class OB_Order {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    @Id    
    private int id;
    
    @Column(name = "version")
    @Id
    private int version;    

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    
    @Column(nullable = false)
    private String symbol;
    
    @Column(nullable = false)
    private BigDecimal price;
    
    @Column(nullable = false)
    private int ordersize;

    @Column(nullable = false)
    private int numbermatched;
    
    @Column(nullable = false)
    private SideEnum side;
    
    @Column(nullable = false)
    private LocalDateTime placedat;
    
    @Column(nullable = false)
    private String usersymbol;
    
    @Column(nullable = false)
    private StatusEnum status;

    public String getUserSymbol() {
        return usersymbol;
    }

    public void setUserSymbol(String usersymbol) {
        this.usersymbol = usersymbol;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

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

    public int getOrdersize() {
        return ordersize;
    }

    public void setOrdersize(int ordersize) {
        this.ordersize = ordersize;
    }

    public int getNumbermatched() {
        return numbermatched;
    }

    public void setNumbermatched(int numbermatched) {
        this.numbermatched = numbermatched;
    }

    public SideEnum getSide() {
        return side;
    }

    public void setSide(SideEnum side) {
        this.side = side;
    }

    public LocalDateTime getPlacedAt() {
        return placedat;
    }

    public void setPlacedAt(LocalDateTime placedat) {
        this.placedat = placedat;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id;
        hash = 71 * hash + Objects.hashCode(this.symbol);
        hash = 71 * hash + Objects.hashCode(this.price);
        hash = 71 * hash + this.ordersize;
        hash = 71 * hash + this.numbermatched;
        hash = 71 * hash + Objects.hashCode(this.side);
        hash = 71 * hash + Objects.hashCode(this.placedat);
        hash = 71 * hash + Objects.hashCode(this.usersymbol);
        hash = 71 * hash + Objects.hashCode(this.status);
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
        final OB_Order other = (OB_Order) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.ordersize != other.ordersize) {
            return false;
        }
        if (this.numbermatched != other.numbermatched) {
            return false;
        }
        if (!Objects.equals(this.symbol, other.symbol)) {
            return false;
        }
        if (!Objects.equals(this.usersymbol, other.usersymbol)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (this.side != other.side) {
            return false;
        }
        if (!Objects.equals(this.placedat, other.placedat)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        return true;
    }
    
}
