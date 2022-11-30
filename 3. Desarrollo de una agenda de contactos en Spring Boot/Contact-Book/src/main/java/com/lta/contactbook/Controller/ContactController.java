package com.lta.contactbook.Controller;

import com.lta.contactbook.Model.Contact;
import com.lta.contactbook.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactController {
    @Autowired
    private ContactService cs;

    @GetMapping("/")
    public String ViewHome(Model model){
        model.addAttribute("conctactL",cs.GetAll());
        model.addAttribute("contactN",new Contact());
    return "Index";
    }

    @GetMapping("/upd/{idC}")
    public String ViewUpdForm(@PathVariable("idC") Long idC,Model model){
        model.addAttribute("contactN",cs.Get(idC).get());
    return "UpdForm";
    }

    @GetMapping("/{idC}")
    public String ViewContactDetails(@PathVariable("idC") Long idC,Model model){
        model.addAttribute("contact",cs.Get(idC));
    return "ViewDet";
    }

    @PostMapping("/add")
    public String AddContact(@Validated @ModelAttribute("contactN") Contact contact,BindingResult bindingResult
                            ,RedirectAttributes redirectAttributes,Model model){
        if(bindingResult.hasErrors()){ //genera nuevo formulario para reintentar limpio de errores
            model.addAttribute("contactN",contact);
            return "Index";
        }
        cs.Save(contact);
        redirectAttributes.addFlashAttribute("msg","Register Contact Successful");
    return "redirect:/";
    }

    @PostMapping("/edit/{idC}")
    public String UpdContact(@ModelAttribute("contact") Contact contact,@PathVariable("idC") Long idC
                            ,RedirectAttributes redirectAttributes){
        contact.setId(idC);
        cs.Update(contact);
        redirectAttributes.addFlashAttribute("msg","Update Contact Successful");
    return "redirect:/";
    }

    @GetMapping("/delete/{idC}")
    public String UpdContact(@PathVariable("idC") Long idC,RedirectAttributes redirectAttributes){
        cs.Delete(idC);
        redirectAttributes.addFlashAttribute("msg","Delete Contact Successful");
        return "redirect:/";
    }
}
