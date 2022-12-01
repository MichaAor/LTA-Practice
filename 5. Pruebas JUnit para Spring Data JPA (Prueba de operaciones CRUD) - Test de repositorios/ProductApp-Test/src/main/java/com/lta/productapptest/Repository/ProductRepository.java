package com.lta.productapptest.Repository;

import com.lta.productapptest.Model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Long> {
    Product findByName(String name);
}
