package com.lta.invmanagapp.Controller;

import com.lta.invmanagapp.Model.Product;
import com.lta.invmanagapp.Service.IService.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService iPs;

    @GetMapping
    public String ViewProducts(Model model){
        model.addAttribute("prodL", iPs.GetAll());
    return "ListPanel";
    }

    @PostMapping
    public String SaveProduct(@ModelAttribute("prodN") Product product){
        iPs.Save(product);
    return "redirect:/products";
    }

}
