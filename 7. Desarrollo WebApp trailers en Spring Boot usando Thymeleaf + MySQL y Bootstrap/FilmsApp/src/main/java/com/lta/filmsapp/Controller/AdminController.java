package com.lta.filmsapp.Controller;

import com.lta.filmsapp.Model.Film;
import com.lta.filmsapp.Model.Genre;
import com.lta.filmsapp.Service.Interface.IFilmService;
import com.lta.filmsapp.Service.Interface.IGenreService;
import com.lta.filmsapp.Service.Interface.IToolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IFilmService ifs;

    @Autowired
    private IGenreService igs;

    @Autowired
    private IToolsService its;

    @GetMapping
    public ModelAndView ViewHome(@PageableDefault(sort = "title",size = 5)Pageable pageable){
        Page<Film> films = ifs.GetAll(pageable);
    return new ModelAndView("admin/Index").addObject("filmL",films);
    }

    @GetMapping("/addF")
    public ModelAndView ViewFilmForm(){
    return new ModelAndView("admin/FilmForm")
            .addObject("film",new Film())
            .addObject("genreL",igs.GetAll());
    }

    @GetMapping("/editF/{idF}")
    public ModelAndView ViewEditForm(@PathVariable("idF")Long idF){
        return new ModelAndView("admin/FilmForm")
                .addObject("filmN",ifs.Get(idF).get())
                .addObject("genreL",igs.GetAll());
    }

    @PostMapping("/addF")
    public ModelAndView SaveFilm(@Validated @ModelAttribute("film") Film film, BindingResult bs){
        if(bs.hasErrors() || film.getCover().isEmpty()){
            if(film.getCover().isEmpty()){
                bs.rejectValue("cover","MultipartNotEmpty");
            }
            List<Genre> genres = igs.GetAll();
            //genres.sort((Genre g1, Genre g2)->g1.getName().compareTo(g2.getName())); Version lambda
            genres.sort(Comparator.comparing(Genre::getName));
            return new ModelAndView("admin/FilmForm")
                    .addObject("film",film)
                    .addObject("genreL",genres);
        }
        String pathCover = its.SaveFile(film.getCover());
        film.setCoverUrl(pathCover);
        ifs.Save(film);
    return new ModelAndView("redirect:/admin");
    }

    @PostMapping("/editF/{idF}")
    public ModelAndView EditFilm(@Validated @ModelAttribute("filmN") Film film,BindingResult bs
                                ,@PathVariable("idF")Long idF){
        if(bs.hasErrors()){
            List<Genre> genres = igs.GetAll();
            //genres.sort((Genre g1, Genre g2)->g1.getName().compareTo(g2.getName())); Version lambda
            genres.sort(Comparator.comparing(Genre::getName));
            return new ModelAndView("admin/FilmForm")
                    .addObject("filmN",film)
                    .addObject("genreL",genres);
        }
        Film filmN = ifs.Get(idF).get();

        filmN.setId(idF);
        filmN.setTitle(film.getTitle());
        filmN.setOverview(film.getOverview());
        filmN.setReleaseD(film.getReleaseD());
        filmN.setYtVID(film.getYtVID());

        if(!film.getCover().isEmpty()){
           its.DeleteFile(filmN.getCoverUrl());
           String coverUrl = its.SaveFile(film.getCover());
           filmN.setCoverUrl(coverUrl);
        }
        ifs.Save(filmN);
    return new ModelAndView("redirect:/admin");
    }

    @GetMapping("/delF/{idF}")
    public ModelAndView DeleteFilm(@PathVariable("idF")Long idF) {

        return null;
    }
}

