package com.mthree.orderbook.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ob_order_audit")
public class OrderAudit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String order_log;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_log() {
        return order_log;
    }

    public void setOrder_log(String order_log) {
        this.order_log = order_log;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderAudit that = (OrderAudit) o;

        if (id != that.id) return false;
        return order_log.equals(that.order_log);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + order_log.hashCode();
        return result;
    }
}
