package com.lta.invmanagapp.Controller;

import com.lta.invmanagapp.Model.Brand;
import com.lta.invmanagapp.Service.IService.IBrandService;
import com.lta.invmanagapp.Service.IService.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/brands")
public class BrandController {
    @Autowired
    private IBrandService iBs;

    @Autowired
    private ICategoryService iCs;

    @GetMapping
    public String ViewBrands(Model model){
        model.addAttribute("brandL",iBs.GetAll());
    return "ListPanel";
    }

    @PostMapping
    public String SaveBrand(@ModelAttribute("brandN")Brand brand){
        iBs.Save(brand);
    return "redirect:/brands";
    }

}
