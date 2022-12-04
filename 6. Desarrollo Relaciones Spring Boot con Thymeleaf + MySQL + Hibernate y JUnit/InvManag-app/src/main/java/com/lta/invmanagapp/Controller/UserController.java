package com.lta.invmanagapp.Controller;

import com.lta.invmanagapp.Model.User;
import com.lta.invmanagapp.Service.IService.IRoleService;
import com.lta.invmanagapp.Service.IService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService iUs;

    @Autowired
    private IRoleService iRs;

    @GetMapping
    public String ViewUsers(Model model){
        model.addAttribute("userL",iUs.GetAll());
    return "ListPanel";
    }

    @PostMapping
    public String SaveUser(@ModelAttribute("userN")User user){
        iUs.Save(user);
    return "redirect:/users";
    }

    @GetMapping("/upd/{idU}")
    public String ViewUpdUser(@PathVariable("idU")Long idU,Model model){
        model.addAttribute("user",iUs.Get(idU).get());
        model.addAttribute("roleL",iRs.GetAll());
        return "UpdatePanel";
    }

    @PostMapping("/upd/{idU}")
    public String UpdateUser(@ModelAttribute("user")User user, @PathVariable("idU")Long idU){
        user.setId(idU);
        iUs.Update(user);
    return "redirect:/users";
    }

    @GetMapping("/del/{idU}")
    public String DeleteUser(@PathVariable("idU")Long idU){
        iUs.Delete(idU);
        return "redirect:/users";
    }
}
