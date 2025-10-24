package com.cinehub.service;

import com.cinehub.dto.FilmDTO;
import com.cinehub.model.Film;
import java.util.List;

public interface FilmService {

    Film create(FilmDTO filmDTO);
    List<Film> getAll();
    Film getById(Long id);
    Film update(Long id, FilmDTO filmDTO);
    void delete(Long id);
    List<Film> findByTitle(String title);
    List<Film> findByReleaseYear(Integer releaseYear);
    List<Film> findByRatingGreaterThanEqual(Double rating);
    List<Film> findByCategory(Long categoryId);
}