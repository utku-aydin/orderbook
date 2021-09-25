package com.mthree.orderbook.entity;

import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "ob_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(
            name = "company_id",
            referencedColumnName = "id",
            nullable = false)
    private Company company;

    @Column(nullable = false)
    private String user_symbol;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getUser_symbol() {
        return user_symbol;
    }

    public void setUser_symbol(String user_symbol) {
        this.user_symbol = user_symbol;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.company);
        hash = 89 * hash + Objects.hashCode(this.user_symbol);
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
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.user_symbol, other.user_symbol)) {
            return false;
        }
        return Objects.equals(this.company, other.company);
    }
    
}
