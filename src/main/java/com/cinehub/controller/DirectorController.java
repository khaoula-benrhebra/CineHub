package com.cinehub.controller;

import com.cinehub.dto.DirectorDTO;
import com.cinehub.model.Director;
import com.cinehub.model.Film;
import com.cinehub.service.DirectorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/directors")
public class DirectorController {

    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @PostMapping
    public ResponseEntity<Director> createDirector(@Valid @RequestBody DirectorDTO directorDTO) {
        Director createdDirector = directorService.create(directorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDirector);
    }

    @GetMapping
    public ResponseEntity<List<Director>> getAllDirectors() {
        List<Director> directors = directorService.getAll();
        return ResponseEntity.ok(directors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Director> getDirectorById(@PathVariable Long id) {
        Director director = directorService.getById(id);
        return ResponseEntity.ok(director);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Director> updateDirector(
            @PathVariable Long id,
            @Valid @RequestBody DirectorDTO directorDTO) {
        Director updatedDirector = directorService.update(id, directorDTO);
        return ResponseEntity.ok(updatedDirector);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable Long id) {
        directorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Director>> searchDirectorsByName(@RequestParam String name) {
        List<Director> directors = directorService.findByName(name);
        return ResponseEntity.ok(directors);
    }

    @GetMapping("/{id}/films")
    public ResponseEntity<List<Film>> getDirectorFilms(@PathVariable Long id) {
        List<Film> films = directorService.getFilmsByDirector(id);
        return ResponseEntity.ok(films);
    }
}