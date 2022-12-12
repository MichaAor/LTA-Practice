package com.lta.inventoryrest.Service;

import com.lta.inventoryrest.Model.Product;
import com.lta.inventoryrest.Repository.ProductRepository;
import com.lta.inventoryrest.Service.Interface.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository pr;


    @Override
    public List<Product> GetAll() {
        return pr.findAll();
    }

    @Override
    public Optional<Product> Get(Long idP) {
        return pr.findById(idP);
    }

    @Override
    public void Save(Product product) {
        pr.save(product);
    }

    @Override
    public void Update(Product product) {
        pr.save(product);
    }

    @Override
    public void Delete(Long idP) {
        pr.deleteById(idP);
    }
}
