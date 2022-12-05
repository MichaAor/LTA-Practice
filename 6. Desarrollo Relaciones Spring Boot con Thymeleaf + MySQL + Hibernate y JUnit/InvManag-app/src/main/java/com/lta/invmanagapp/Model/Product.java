package com.lta.invmanagapp.Model;

import lombok.*;
import org.hibernate.annotations.NotFound;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 128,nullable = false,unique = true)
    private String name;
    private float price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    //(mappedBy='referencia al elementos de la clase',
    // cascade= formato)Permite que persista a los objetos relacionados a este
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<ProductDetail> details = new ArrayList<>();

    public void addDetails(String name, String value){
        this.details.add(new ProductDetail(name,value,this));
    }
    public void setDetail(Long id,String name,String value){
        this.details.add(new ProductDetail(id,name,value,this));
    }
}
