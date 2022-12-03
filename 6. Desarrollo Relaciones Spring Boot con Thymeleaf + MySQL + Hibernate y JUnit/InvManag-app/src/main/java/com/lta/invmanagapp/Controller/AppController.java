package com.lta.invmanagapp.Controller;

import com.lta.invmanagapp.Model.Brand;
import com.lta.invmanagapp.Model.Category;
import com.lta.invmanagapp.Model.Product;
import com.lta.invmanagapp.Service.IService.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @Autowired
    private ICategoryService iCs;
    @GetMapping
    public String ViewHome(Model model){
        model.addAttribute("catN",new Category());
        model.addAttribute("prodN",new Product());
        model.addAttribute("catL",iCs.GetAll());
        model.addAttribute("brandN",new Brand());
        return "Index";
    }
}

