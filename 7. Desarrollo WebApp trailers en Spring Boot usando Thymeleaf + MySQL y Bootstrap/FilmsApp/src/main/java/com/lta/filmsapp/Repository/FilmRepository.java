package com.lta.filmsapp.Repository;

import com.lta.filmsapp.Model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film,Long> {
}
