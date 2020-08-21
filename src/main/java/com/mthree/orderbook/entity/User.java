package com.mthree.orderbook.entity;

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
            nullable = false),
    private Company company;

    @Column(nullable = false)
    private int usr_id;
    
}
