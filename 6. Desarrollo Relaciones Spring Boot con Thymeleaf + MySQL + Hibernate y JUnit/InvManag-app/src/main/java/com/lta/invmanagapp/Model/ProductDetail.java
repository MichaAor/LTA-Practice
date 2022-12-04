package com.lta.invmanagapp.Model;

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
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 45,nullable = false)
    private String name;
    @Column(length = 45,nullable = false)
    private String value;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductDetail(String name,String value,Product product){
        this.name = name;
        this.value = value;
        this.product = product;
    }
}
