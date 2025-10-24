package com.cinehub.mapper;

import com.cinehub.model.Film;
import com.cinehub.dto.FilmDTO;
import com.cinehub.model.Director;
import com.cinehub.model.Category;
import org.springframework.stereotype.Component;

@Component
public class FilmMapper {

    public FilmDTO toDTO(Film film) {
        if (film == null) {
            return null;
        }

        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setTitle(film.getTitle());
        filmDTO.setReleaseYear(film.getReleaseYear());
        filmDTO.setDuration(film.getDuration());
        filmDTO.setSynopsis(film.getSynopsis());
        filmDTO.setRating(film.getRating());

        if (film.getDirector() != null) {
            filmDTO.setDirectorId(film.getDirector().getIdDirector());
        }

        if (film.getCategory() != null) {
            filmDTO.setCategoryId(film.getCategory().getIdCategory());
        }

        return filmDTO;
    }

    public Film toEntity(FilmDTO filmDTO, Director director, Category category) {
        if (filmDTO == null) {
            return null;
        }

        Film film = new Film();
        film.setTitle(filmDTO.getTitle());
        film.setReleaseYear(filmDTO.getReleaseYear());
        film.setDuration(filmDTO.getDuration());
        film.setSynopsis(filmDTO.getSynopsis());
        film.setRating(filmDTO.getRating());
        film.setDirector(director);
        film.setCategory(category);

        return film;
    }
}