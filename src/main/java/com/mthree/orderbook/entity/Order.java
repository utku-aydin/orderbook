package com.mthree.orderbook.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "ob_order")
public class Order implements Serializable {
    
    @EmbeddedId
    private OrderId id;  
    
    @ManyToOne
    @JoinColumn(name = "usr_id", nullable = false)
    private User user;
    
    @Column(nullable = false)
    private BigDecimal price;
    
    @Column(nullable = false)
    private int order_size;

    @Column(nullable = false)
    private int number_matched;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SideEnum side;
    
    @Column(nullable = false)
    private LocalDateTime placed_at;
    
    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEnum status;

    public OrderId getId() {
        return id;
    }

    public void setId(OrderId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getOrder_size() {
        return order_size;
    }

    public void setOrder_size(int order_size) {
        this.order_size = order_size;
    }

    public int getNumber_matched() {
        return number_matched;
    }

    public void setNumber_matched(int number_matched) {
        this.number_matched = number_matched;
    }

    public SideEnum getSide() {
        return side;
    }

    public void setSide(SideEnum side) {
        this.side = side;
    }

    public LocalDateTime getPlaced_at() {
        return placed_at;
    }

    public void setPlaced_at(LocalDateTime placed_at) {
        this.placed_at = placed_at;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.user);
        hash = 29 * hash + Objects.hashCode(this.price);
        hash = 29 * hash + this.order_size;
        hash = 29 * hash + this.number_matched;
        hash = 29 * hash + Objects.hashCode(this.side);
        hash = 29 * hash + Objects.hashCode(this.placed_at);
        hash = 29 * hash + Objects.hashCode(this.stock);
        hash = 29 * hash + Objects.hashCode(this.status);
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
        if (this.order_size != other.order_size) {
            return false;
        }
        if (this.number_matched != other.number_matched) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (this.side != other.side) {
            return false;
        }
        if (!Objects.equals(this.placed_at, other.placed_at)) {
            return false;
        }
        if (!Objects.equals(this.stock, other.stock)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OB_Order{" + "id=" + id + ", user=" + user + ", price=" + price + ", order_size=" + order_size + ", number_matched=" + number_matched + ", side=" + side + ", placed_at=" + placed_at + ", stock=" + stock + ", status=" + status + '}';
    }
    
}
