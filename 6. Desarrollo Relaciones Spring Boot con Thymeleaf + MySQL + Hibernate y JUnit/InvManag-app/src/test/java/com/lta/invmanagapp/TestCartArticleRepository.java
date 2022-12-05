package com.lta.invmanagapp;

import com.lta.invmanagapp.Model.CartArticle;
import com.lta.invmanagapp.Model.Product;
import com.lta.invmanagapp.Model.User;
import com.lta.invmanagapp.Repository.CartArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class TestCartArticleRepository {
    @Autowired
    private CartArticleRepository caR;
    @Autowired
    private EntityManager entityManager;

    @Test
    public void TestAddCartArt(){
        Product product = entityManager.find(Product.class,2L);
        User user = entityManager.find(User.class,3L);
        CartArticle caN = caR.save(new CartArticle(product,user,1));
        assertThat(caN.getId()).isGreaterThan(0);
    }

    @Test
    public void TestAddMultipleArt(){
        Product prod = new Product();
        Product prod2 = new Product();
        prod.setId(2L); prod2.setId(3L);
        User user = new User();
        user.setId(1L);
        CartArticle car1 = new CartArticle(prod,user,1);
        CartArticle car2 = new CartArticle(prod2,user,10);
        List<CartArticle> carArtL = caR.saveAll(List.of(car1,car2));
        Assert.notEmpty(carArtL,"The list is not empty");
    }

    @Test
    public void TestListCartArt(){
        List<CartArticle> carArtL = caR.findAll();
        carArtL.forEach(System.out::println);
    }

    @Test
    public void TestUpdateCartArt(){
        CartArticle carArt = caR.findById(3L).get();
        carArt.setAmount(15);
    }

    @Test
    public void TestDeleteCartArt(){
        caR.deleteById(2L);
    }
}
