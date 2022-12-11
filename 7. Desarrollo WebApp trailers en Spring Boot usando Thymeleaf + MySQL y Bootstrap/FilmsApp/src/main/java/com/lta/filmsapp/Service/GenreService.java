package com.lta.filmsapp.Service;

import com.lta.filmsapp.Model.Genre;
import com.lta.filmsapp.Repository.GenreRepository;
import com.lta.filmsapp.Service.Interface.IGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService implements IGenreService {
    @Autowired
    private GenreRepository gr;

    @Override
    public List<Genre> GetAll() {
        return gr.findAll();
    }

    @Override
    public Optional<Genre> Get(Long idG) {
        return gr.findById(idG);
    }

    @Override
    public void Save(Genre genre) {
        gr.save(genre);
    }

    @Override
    public void Update(Genre genre) {
        gr.save(genre);
    }

    @Override
    public void Delete(Long idG) {
        gr.deleteById(idG);
    }
}
