package com.piseth.java.school.phoneshop.phoneshop.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "models")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne()
    @JoinColumn(name = "brand_id")  // using Column will error
    private Brand brand;
}
