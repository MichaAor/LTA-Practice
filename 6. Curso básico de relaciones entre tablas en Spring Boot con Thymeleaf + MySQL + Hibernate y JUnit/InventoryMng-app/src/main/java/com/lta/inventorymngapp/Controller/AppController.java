package com.lta.inventorymngapp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping
    public String ViewHome(){
        return "Index";
    }
}

