package com.lta.inventorymngapp.Service;

import com.lta.inventorymngapp.Model.Category;
import com.lta.inventorymngapp.Repository.CategoryRepository;
import com.lta.inventorymngapp.Service.IService.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository cr;


    @Override
    public List<Category> GetAll() {
        return cr.findAll();
    }

    @Override
    public Optional<Category> Get(Long idC) {
        return cr.findById(idC);
    }

    @Override
    public void Save(Category category) {
        cr.save(category);
    }

    @Override
    public void Update(Category category) {
        cr.save(category);
    }

    @Override
    public void Delete(Long idC) {
        cr.deleteById(idC);
    }
}

