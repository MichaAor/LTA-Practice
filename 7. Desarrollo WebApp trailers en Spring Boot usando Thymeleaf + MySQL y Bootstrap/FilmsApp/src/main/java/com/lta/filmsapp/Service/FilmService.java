package com.lta.filmsapp.Service;

import com.lta.filmsapp.Model.Film;
import com.lta.filmsapp.Repository.FilmRepository;
import com.lta.filmsapp.Service.Interface.IFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmService implements IFilmService {
    @Autowired
    private FilmRepository fr;


    @Override
    public Page<Film> GetAll(Pageable pageable) {
        return fr.findAll(pageable);
    }

    @Override
    public Optional<Film> Get(Long idF) {
        return fr.findById(idF);
    }

    @Override
    public void Save(Film film) {
        fr.save(film);
    }

    @Override
    public void Update(Film film) {
        fr.save(film);
    }

    @Override
    public void Delete(Long idF) {
        fr.deleteById(idF);
    }
}
