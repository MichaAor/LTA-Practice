package com.lta.producmanag.Controller;

import com.lta.producmanag.Model.Product;
import com.lta.producmanag.Service.Interface.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {
    @Autowired
    private IProductService ips;

    @GetMapping
    public ModelAndView ViewHome(@Param("search")String search){
        return new ModelAndView("Index")
                .addObject("prodL",ips.GetAll(search))
                .addObject("search",search)
                .addObject("prodN",new Product());
    }

    @GetMapping("/{search}")
    public ModelAndView FilterBySearch(){
     return new ModelAndView();
    }

    @PostMapping("/reg")
    public ModelAndView SaveProduct(@ModelAttribute("prodN")Product product){
        ips.Save(product);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/upd/{idP}")
    public ModelAndView SaveProduct(@PathVariable("idP")Long idP){
        return new ModelAndView("Index").addObject("prodE",ips.Get(idP).get());
    }

    @PostMapping("/upd/{idP}")
    public ModelAndView SaveProduct(@PathVariable("idP")Long idP,@ModelAttribute("prodN")Product product){
        Product prN = ips.Get(idP).get();
        prN.setId(idP);
        prN.setName(product.getName());
        prN.setBrand(product.getBrand());
        prN.setOrigin(product.getOrigin());
        prN.setPrice(product.getPrice());

        ips.Update(prN);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/del/{idP}")
    public ModelAndView DeleteProduct(@PathVariable("idP")Long idP){
        ips.Delete(idP);
        return new ModelAndView("redirect:/");
    }
}
