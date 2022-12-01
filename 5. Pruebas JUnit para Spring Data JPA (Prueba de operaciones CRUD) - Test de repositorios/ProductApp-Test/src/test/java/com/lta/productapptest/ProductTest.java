package com.lta.productapptest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.lta.productapptest.Model.Product;
import com.lta.productapptest.Repository.ProductRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //Realizar operaciones a la BDD REAL
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)                        //NO H2, SINO LA MYSQL
public class ProductTest {
    @Autowired
    private ProductRepository pr;

    @Test
    @Rollback(value = false)    ///Si no se agrega, hace rollback (borrado de lo recien insertado en bdd)
    @Order(1)
    public void TestSavePr(){
        Product product = new Product("SmartTV Samsung 50'",85100.0f);
        Product prSave = pr.save(product);

        assertNotNull(prSave);
    }

    @Test
    @Rollback(value = false)
    @Order(2)
    public void TestUpdatePr(){
        Product product = new Product(11L,"SmartTV Sony 50'",85000.5f);
        pr.save(product);
        Product prUpdate = pr.findByName("SmartTV Sony 50'");

        assertThat(prUpdate.getName()).isEqualTo(product.getName());
    }

    @Test
    @Order(3)
    public void TestGetAllPr(){
        List<Product> prL = (List<Product>) pr.findAll();
        assertThat(prL).size().isGreaterThan(0);
        prL.forEach(System.out::println);
    }

    @Test
    @Order(4)
    public void TestGetByNamePrNoExist(){
        String name = "SmartTV Samsung 50'";
        Product product = pr.findByName(name);
        assertNull(product);
    }

    @Test
    @Order(5)
    public void TestGetByNamePrExist(){
        String name = "SmartTV Sony 50'";
        Product product = pr.findByName(name);

        assertThat(product.getName()).isEqualTo(name);
    }

    @Test
    @Rollback(value = false)
    @Order(6)
    public void TestDeletePr(){
        boolean beforeTest = pr.findById(11L).isPresent();
        pr.deleteById(11L);
        boolean afterTest = pr.findById(11L).isPresent();

        assertTrue(beforeTest);
        assertFalse(afterTest);
    }
}

