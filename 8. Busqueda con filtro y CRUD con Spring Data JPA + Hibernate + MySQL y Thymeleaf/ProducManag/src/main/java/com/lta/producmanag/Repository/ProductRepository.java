package com.lta.producmanag.Repository;

import com.lta.producmanag.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    //@Query("SELECT p FROM Product p WHERE p.name LIKE %?1% OR p.brand LIKE %?1% OR p.origin LIKE %?1%")
    @Query("SELECT p FROM Product p WHERE CONCAT(p.id,p.name,p.brand,p.origin) LIKE %?1%")
    List<Product> findAllFilter(String search);
}
