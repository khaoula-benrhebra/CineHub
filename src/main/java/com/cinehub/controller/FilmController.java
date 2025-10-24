package com.cinehub.controller;

import com.cinehub.dto.FilmDTO;
import com.cinehub.model.Film;
import com.cinehub.service.FilmService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping
    public ResponseEntity<Film> createFilm(@Valid @RequestBody FilmDTO filmDTO) {
        Film createdFilm = filmService.create(filmDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFilm);
    }

    @GetMapping
    public ResponseEntity<List<Film>> getAllFilms() {
        List<Film> films = filmService.getAll();
        return ResponseEntity.ok(films);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable Long id) {
        Film film = filmService.getById(id);
        return ResponseEntity.ok(film);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Film> updateFilm(
            @PathVariable Long id,
            @Valid @RequestBody FilmDTO filmDTO) {
        Film updatedFilm = filmService.update(id, filmDTO);
        return ResponseEntity.ok(updatedFilm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        filmService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Film>> searchFilmsByTitle(@RequestParam String title) {
        List<Film> films = filmService.findByTitle(title);
        return ResponseEntity.ok(films);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<Film>> getFilmsByYear(@PathVariable Integer year) {
        List<Film> films = filmService.findByReleaseYear(year);
        return ResponseEntity.ok(films);
    }

    @GetMapping("/rating/{minRating}")
    public ResponseEntity<List<Film>> getFilmsByMinRating(@PathVariable Double minRating) {
        List<Film> films = filmService.findByRatingGreaterThanEqual(minRating);
        return ResponseEntity.ok(films);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Film>> getFilmsByCategory(@PathVariable Long categoryId) {
        List<Film> films = filmService.findByCategory(categoryId);
        return ResponseEntity.ok(films);
    }
}