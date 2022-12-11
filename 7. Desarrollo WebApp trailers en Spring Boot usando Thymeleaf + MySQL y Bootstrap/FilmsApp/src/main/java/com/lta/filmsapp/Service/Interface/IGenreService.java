package com.lta.filmsapp.Service.Interface;

import com.lta.filmsapp.Model.Genre;

import java.util.List;
import java.util.Optional;

public interface IGenreService {
    List<Genre> GetAll();
    Optional<Genre> Get(Long idG);
    void Save(Genre genre);
    void Update(Genre genre);
    void Delete(Long idG);
}
