package com.lta.filmsapp.Controller;

import com.lta.filmsapp.Service.Interface.IFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lta.filmsapp.Model.Film;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private IFilmService ifs;

    @GetMapping
    public ModelAndView ViewHome(){
        List<Film> lastFL = ifs.GetAll(PageRequest.of(0,4, Sort.by("releaseD").descending())).toList();
        return new ModelAndView("Index").addObject("lastFL",lastFL);
    }
}
