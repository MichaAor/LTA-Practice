package com.lta.invmanagapp.Service;

import com.lta.invmanagapp.Model.Brand;
import com.lta.invmanagapp.Repository.BrandRepository;
import com.lta.invmanagapp.Service.IService.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService implements IBrandService{
    @Autowired
    private BrandRepository br;
    @Override
    public List<Brand> GetAll() {
        return br.findAll();
    }

    @Override
    public Optional<Brand> Get(Long idB) {
        return br.findById(idB);
    }

    @Override
    public void Save(Brand brand) {
        br.save(brand);
    }

    @Override
    public void Update(Brand brand) {
        br.save(brand);
    }

    @Override
    public void Delete(Long idB) {
        br.deleteById(idB);
    }
}