package com.lta.invmanagapp.Controller;

import com.lta.invmanagapp.Model.Category;
import com.lta.invmanagapp.Service.IService.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/upd/{idC}")
    public String ViewpUpdCategory(Model model,@PathVariable("idC") Long idC){
        model.addAttribute("cat",cs.Get(idC).get());
    return "UpdatePanel";
    }

    @PostMapping
    public String SaveCategory(@ModelAttribute("catN")Category catN){
            cs.Save(catN);
    return "redirect:/categories";
    }
    @PostMapping("/upd/{idC}")
    public String SaveCategory(@PathVariable("idC")Long idC
                              ,@ModelAttribute("catN")Category catN){
        catN.setId(idC);
        cs.Update(catN);
        return "redirect:/categories";
    }

    @GetMapping("/del/{idC}")
    public String DeleteCategory(@PathVariable("idC")Long idC){
        cs.Delete(idC);
    return "redirect:/categories";
    }
}

