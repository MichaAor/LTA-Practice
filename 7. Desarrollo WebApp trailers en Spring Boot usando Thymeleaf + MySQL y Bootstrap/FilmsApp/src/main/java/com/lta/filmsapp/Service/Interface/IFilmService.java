package com.lta.filmsapp.Service.Interface;

import com.lta.filmsapp.Model.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IFilmService {
    Page<Film> GetAll(Pageable pageable);
    Optional<Film> Get(Long idF);
    void Save(Film film);
    void Update(Film film);
    void Delete(Long idF);
}
