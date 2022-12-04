package com.lta.invmanagapp.Controller;

import com.lta.invmanagapp.Model.Brand;
import com.lta.invmanagapp.Service.IService.IBrandService;
import com.lta.invmanagapp.Service.IService.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/upd/{idB}")
    public String ViewUpdBrand(Model model, @PathVariable("idB")Long idB){
        model.addAttribute("brand",iBs.Get(idB).get());
        model.addAttribute("catL",iCs.GetAll());
    return "UpdatePanel";
    }

    @PostMapping
    public String SaveBrand(@ModelAttribute("brandN")Brand brand){
        iBs.Save(brand);
    return "redirect:/brands";
    }

    @PostMapping("/upd/{idB}")
    public String UpdateBrand(@PathVariable("idB") Long idB,@ModelAttribute("Brand") Brand brand){
        brand.setId(idB);
        iBs.Update(brand);
    return "redirect:/brands";
    }

    @GetMapping("/del/{idB}")
    public String DeleteBrand(@PathVariable("idB") Long idB){
        iBs.Delete(idB);
    return "redirect:/brands";
    }
}
