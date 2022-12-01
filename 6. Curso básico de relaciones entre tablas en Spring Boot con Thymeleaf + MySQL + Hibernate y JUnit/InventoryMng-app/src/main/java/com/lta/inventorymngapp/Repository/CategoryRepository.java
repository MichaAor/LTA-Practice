package com.lta.inventorymngapp.Repository;


import com.lta.inventorymngapp.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
