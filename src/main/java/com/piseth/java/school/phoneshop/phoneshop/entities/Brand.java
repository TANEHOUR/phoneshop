package com.piseth.java.school.phoneshop.phoneshop.entities;

import lombok.Data;

import javax.persistence.*;

@Data   // lombok
@Entity // from jpa
@Table(name = "brands") // convention with (s) customize table

public class Brand {
    @Id // Error no identifier if we don't use id annotation
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto generate id for us
    @Column(name = "brand_id")  // customize column
    private Integer id;     // recommend Long
    @Column(name = "brand_name")
    private String name;


}
