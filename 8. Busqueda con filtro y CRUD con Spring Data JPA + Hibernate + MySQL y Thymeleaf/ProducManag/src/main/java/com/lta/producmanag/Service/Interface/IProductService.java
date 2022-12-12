package com.lta.producmanag.Service.Interface;

import com.lta.producmanag.Model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product>GetAll(String filter);
    Optional<Product> Get(Long idP);
    void Save(Product product);
    void Update(Product product);
    void Delete(Long idP);
}
