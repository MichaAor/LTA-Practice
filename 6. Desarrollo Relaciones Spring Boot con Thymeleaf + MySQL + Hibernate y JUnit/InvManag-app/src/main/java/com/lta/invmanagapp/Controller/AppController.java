package com.lta.invmanagapp.Controller;

import com.lta.invmanagapp.Model.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping
    public String ViewHome(Model model){
        model.addAttribute("catN",new Category());
        return "Index";
    }
}

