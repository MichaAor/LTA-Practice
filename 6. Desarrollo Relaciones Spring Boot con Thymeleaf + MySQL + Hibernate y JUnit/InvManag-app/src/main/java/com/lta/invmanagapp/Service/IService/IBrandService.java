package com.lta.invmanagapp.Service.IService;

import com.lta.invmanagapp.Model.Brand;

import java.util.List;
import java.util.Optional;

public interface IBrandService {
    List<Brand> GetAll();
    Optional<Brand> Get(Long idB);
    void Save(Brand brand);
    void Update(Brand brand);
    void Delete(Long idB);
}
