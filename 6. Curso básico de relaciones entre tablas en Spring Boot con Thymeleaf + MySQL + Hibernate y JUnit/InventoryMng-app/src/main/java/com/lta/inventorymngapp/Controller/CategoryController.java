package com.lta.inventorymngapp.Controller;

import com.lta.inventorymngapp.Service.IService.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ICategoryService cs;

    @GetMapping
    public String ViewCategories(Model model){
        model.addAttribute("catL",cs.GetAll());
    return "ListPanel";
    }
}

