package com.lta.invmanagapp.Model;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CartArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    private int amount;

    public CartArticle(Product product,User user,int amount){
        this.product = product;
        this.user = user;
        this.amount = amount;
    }
}
