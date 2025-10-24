package com.cinehub.service;

import com.cinehub.dto.DirectorDTO;
import com.cinehub.model.Director;
import com.cinehub.model.Film;

import java.util.List;

public interface DirectorService {

    Director create(DirectorDTO directorDTO);
    List<Director> getAll();
    Director getById(Long id);
    Director update(Long id, DirectorDTO directorDTO);
    void delete(Long id);
    List<Director> findByName(String name);
    List<Film> getFilmsByDirector(Long directorId);
}