package com.lta.producmanag.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 60)
    private String name;
    @Column(nullable = false,length = 60)
    private String brand;
    @Column(nullable = false,length = 60)
    private String origin;
    @Column(nullable = false)
    private float price;
}
