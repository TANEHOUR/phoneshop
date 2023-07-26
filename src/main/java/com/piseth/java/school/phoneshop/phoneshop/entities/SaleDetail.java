package com.piseth.java.school.phoneshop.phoneshop.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "saleDetails")

public class SaleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_detail_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @Column(name = "sold_date")
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer unit;
}
