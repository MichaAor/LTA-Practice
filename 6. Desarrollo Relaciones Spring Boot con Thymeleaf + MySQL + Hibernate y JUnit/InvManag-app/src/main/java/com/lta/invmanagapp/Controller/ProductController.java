package com.lta.invmanagapp.Controller;

import com.lta.invmanagapp.Model.Product;
import com.lta.invmanagapp.Service.IService.ICategoryService;
import com.lta.invmanagapp.Service.IService.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService iPs;

    @Autowired
    private ICategoryService iCs;

    @GetMapping
    public String ViewProducts(Model model){
        model.addAttribute("prodL", iPs.GetAll());
    return "ListPanel";
    }

    @GetMapping("/upd/{idP}")
    public String ViewUpdProduct(Model model, @PathVariable("idP") Long idP){
        model.addAttribute("prod",iPs.Get(idP).get());
        model.addAttribute("catL",iCs.GetAll());
    return "UpdatePanel";
    }

    @PostMapping
    public String SaveProduct(@ModelAttribute("prodN") Product product, HttpServletRequest request){
        String[] detailsNames = request.getParameterValues("detN");
        String[] detailsValues = request.getParameterValues("detV");
        for(int i=0; i< detailsNames.length;i++){
            product.addDetails(detailsNames[i],detailsValues[i]);
        }
        iPs.Save(product);
    return "redirect:/products";
    }

    @PostMapping("/upd/{idP}")
    public String UpdateProduct(@PathVariable("idP") Long idP,
                                @ModelAttribute("prod") Product product
                                ,HttpServletRequest request){
        String[] detailsId = request.getParameterValues("detId");
        String[] detailsNames = request.getParameterValues("detN");
        String[] detailsValues = request.getParameterValues("detV");
        product.setId(idP);
        for(int i=0; i< detailsNames.length;i++){
            product.setDetail(Long.valueOf(detailsId[i]),detailsNames[i],detailsValues[i]);
        }
        iPs.Update(product);
    return "redirect:/products";
    }

    @GetMapping("/del/{idP}")
    public String DeleteProduct(@PathVariable("idP") Long idP){
        iPs.Delete(idP);
    return "redirect:/products";
    }

}
