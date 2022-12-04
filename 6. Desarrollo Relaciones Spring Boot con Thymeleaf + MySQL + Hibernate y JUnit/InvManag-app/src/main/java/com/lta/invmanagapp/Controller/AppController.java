package com.lta.invmanagapp.Controller;

import com.lta.invmanagapp.Model.Brand;
import com.lta.invmanagapp.Model.Category;
import com.lta.invmanagapp.Model.Product;
import com.lta.invmanagapp.Model.User;
import com.lta.invmanagapp.Service.IService.ICategoryService;
import com.lta.invmanagapp.Service.IService.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @Autowired
    private ICategoryService iCs;

    @Autowired
    private IRoleService iRs;

    @GetMapping
    public String ViewHome(Model model){
        model.addAttribute("catN",new Category());
        model.addAttribute("prodN",new Product());
        model.addAttribute("catL",iCs.GetAll());
        model.addAttribute("brandN",new Brand());
        model.addAttribute("userN",new User());
        model.addAttribute("roleL",iRs.GetAll());
        return "Index";
    }
}

