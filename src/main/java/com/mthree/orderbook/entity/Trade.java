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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author utkua
 */
@Entity
@Table(name = "trade")
public class Trade implements Serializable {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    @Id    
    private int id;
    
    // Don't capitalize id, as JPA will add an underscore (since MySql not case-sensitive)
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "buyid", nullable = false),
        @JoinColumn(name = "buyversion", nullable = false)
    })
    private OB_Order buyorder;
    
    // Don't capitalize id, as JPA will add an underscore (since MySql not case-sensitive)
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "sellid", nullable = false),
        @JoinColumn(name = "sellversion", nullable = false)
    })
    private OB_Order sellorder;
    
    @Column(nullable = false)
    private LocalDateTime tradetime;
    
    @Column(nullable = false)
    private BigDecimal tradeprice;
    
    @Column(nullable = false)
    private int tradesize;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OB_Order getBuyorder() {
        return buyorder;
    }

    public void setBuyorder(OB_Order buyorder) {
        this.buyorder = buyorder;
    }

    public OB_Order getSellorder() {
        return sellorder;
    }

    public void setSellorder(OB_Order sellorder) {
        this.sellorder = sellorder;
    }

    public LocalDateTime getTradetime() {
        return tradetime;
    }

    public void setTradetime(LocalDateTime tradetime) {
        this.tradetime = tradetime;
    }

    public BigDecimal getTradeprice() {
        return tradeprice;
    }

    public void setTradeprice(BigDecimal tradeprice) {
        this.tradeprice = tradeprice;
    }

    public int getTradesize() {
        return tradesize;
    }

    public void setTradesize(int tradesize) {
        this.tradesize = tradesize;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.id;
        hash = 73 * hash + Objects.hashCode(this.buyorder);
        hash = 73 * hash + Objects.hashCode(this.sellorder);
        hash = 73 * hash + Objects.hashCode(this.tradetime);
        hash = 73 * hash + Objects.hashCode(this.tradeprice);
        hash = 73 * hash + this.tradesize;
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
        if (this.tradesize != other.tradesize) {
            return false;
        }
        if (!Objects.equals(this.buyorder, other.buyorder)) {
            return false;
        }
        if (!Objects.equals(this.sellorder, other.sellorder)) {
            return false;
        }
        if (!Objects.equals(this.tradetime, other.tradetime)) {
            return false;
        }
        if (!Objects.equals(this.tradeprice, other.tradeprice)) {
            return false;
        }
        return true;
    }
    
}
