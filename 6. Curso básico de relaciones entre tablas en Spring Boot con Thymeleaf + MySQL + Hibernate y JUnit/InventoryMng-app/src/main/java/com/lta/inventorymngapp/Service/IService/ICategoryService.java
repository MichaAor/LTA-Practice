package com.lta.inventorymngapp.Service.IService;

import com.lta.inventorymngapp.Model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> GetAll();
    Optional<Category> Get(Long idC);
    void Save(Category category);
    void Update(Category category);
    void Delete(Long idC);
}
