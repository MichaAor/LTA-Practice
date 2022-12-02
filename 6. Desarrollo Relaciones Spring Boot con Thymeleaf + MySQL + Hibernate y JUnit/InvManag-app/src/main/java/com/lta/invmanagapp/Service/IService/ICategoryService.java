package com.lta.invmanagapp.Service.IService;

import com.lta.invmanagapp.Model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> GetAll();
    Optional<Category> Get(Long idC);
    void Save(Category category);
    void Update(Category category);
    void Delete(Long idC);
}
