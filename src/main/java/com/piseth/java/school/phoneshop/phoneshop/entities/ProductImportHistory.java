package com.piseth.java.school.phoneshop.phoneshop.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "productsImportHistories")

public class ProductImportHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "import_id")
    private Long id;

    @Column(name = "date_import")
    private LocalDate dateImport;

    @JoinColumn(name = "import_unit")
    private Integer importUnit;

    @JoinColumn(name = "price_per_unit")
    private BigDecimal pricePerUnit;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


}
