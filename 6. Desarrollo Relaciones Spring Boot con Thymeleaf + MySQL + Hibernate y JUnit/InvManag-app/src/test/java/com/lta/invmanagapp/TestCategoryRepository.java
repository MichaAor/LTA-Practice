package com.lta.invmanagapp;

import com.lta.invmanagapp.Model.Category;
import com.lta.invmanagapp.Repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class TestCategoryRepository {
    @Autowired
    private CategoryRepository cr;

    @Test
    public void TestCategorySave(){
        Category catN = cr.save(new Category("SmartTV"));
        assertThat(catN.getId()).isGreaterThan(0);
    }

    @Test
    public void TestCategoryUpdate(){
        Category catN = cr.save(new Category(2L,"Tablet"));
    }

    @Test
    public void TestCategoryGetAll(){

    }

    @Test
    public void TestCategoryGetBy(){

    }

    @Test
    public void TestCategoryDelete(){

    }

}
