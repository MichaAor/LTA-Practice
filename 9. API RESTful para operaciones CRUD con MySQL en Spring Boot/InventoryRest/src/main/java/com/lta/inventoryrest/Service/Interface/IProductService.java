package com.lta.inventoryrest.Service.Interface;

import com.lta.inventoryrest.Model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> GetAll();
    Optional<Product>Get(Long idP);
    void Save(Product product);
    void Update(Product product);
    void Delete(Long idP);

}
