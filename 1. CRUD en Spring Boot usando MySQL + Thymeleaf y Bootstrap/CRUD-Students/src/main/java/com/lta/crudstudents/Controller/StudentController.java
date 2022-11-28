package com.lta.crudstudents.Controller;

import com.lta.crudstudents.Model.Student;
import com.lta.crudstudents.Service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class StudentController{
    @Autowired
    private IStudentService iss;

/**
 * Views Controllers
 */
    @GetMapping
    public String ViewIndex(Model model){
        List<Student> stL = iss.GetAll();
        Student stN = new Student();
        model.addAttribute("students",stL);
        model.addAttribute("stN",stN);
        return "Index";
    }

    @GetMapping("/edit/{idSt}")
    public String ViewUpdateForm(@PathVariable("idSt") Long idSt,Model model){
        Optional<Student> st = iss.Get(idSt);
        if(st.isPresent()){
            model.addAttribute("student",st.get());
            return "ViewAddStudent";
        }
    return "redirect:/";
    }

/**
* Operational Controllers
*/
    @PostMapping("/add")
    public String AddST(@ModelAttribute("student") Student student){
        iss.Save(student);
        return "redirect:/";
    }

    @PostMapping("/edit/{idST}")
    public String UpdateST(@PathVariable("idST") Long idST,@ModelAttribute("student") Student stF){
        Student st = iss.Get(idST).get();
        st.setId(idST);
        st.setName(stF.getName());
        st.setLastname(stF.getLastname());
        st.setEmail(stF.getEmail());
        iss.Update(st);
        return "redirect:/";
    }

    @GetMapping("/delete/{idST}")
    public String DeleteST(@PathVariable("idST")Long idST){
        iss.Delete(idST);
        return "redirect:/";
    }
}
