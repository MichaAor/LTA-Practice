package com.lta.userspringsecurity.Controller;

import com.lta.userspringsecurity.DTO.UserDTO;
import com.lta.userspringsecurity.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private IUserService iUs;

    @ModelAttribute("user")
    public UserDTO returnNewUserDTO(){
        return new UserDTO();
    }

    @GetMapping("/register")
    public String ViewRegister(){
        return "Register";
    }

    @GetMapping("/")
    public String ViewHome(Model model){
        model.addAttribute("userL", iUs.GetAll());
        return "Index";
    }

    @PostMapping("/register")
    public String Register(@ModelAttribute("user")UserDTO userDTO){
        iUs.Save(userDTO);
    return "redirect:/register?successful";     //return "redirect:/mapping?parametter or value";
    }

}

