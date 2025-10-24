package com.cinehub.service;

import com.cinehub.dto.FilmDTO;
import com.cinehub.exception.CategoryAlreadyExistsException;
import com.cinehub.exception.CategoryNotFoundException;
import com.cinehub.mapper.FilmMapper;
import com.cinehub.model.Film;
import com.cinehub.model.Director;
import com.cinehub.model.Category;
import com.cinehub.repository.FilmRepository;
import com.cinehub.repository.DirectorRepository;
import com.cinehub.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final DirectorRepository directorRepository;
    private final CategoryRepository categoryRepository;
    private final FilmMapper filmMapper;

    public FilmServiceImpl(FilmRepository filmRepository,
                           DirectorRepository directorRepository,
                           CategoryRepository categoryRepository,
                           FilmMapper filmMapper) {
        this.filmRepository = filmRepository;
        this.directorRepository = directorRepository;
        this.categoryRepository = categoryRepository;
        this.filmMapper = filmMapper;
    }


    @Override
    public Film create(FilmDTO filmDTO) {
        // Vérifier si un film avec le même titre existe déjà
        if (filmRepository.existsByTitle(filmDTO.getTitle())) {
            throw new CategoryAlreadyExistsException(
                    "Un film avec le titre '" + filmDTO.getTitle() + "' existe déjà"
            );
        }

        Director director = directorRepository.findById(filmDTO.getDirectorId())
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Réalisateur non trouvé avec l'ID : " + filmDTO.getDirectorId()
                ));


        Category category = categoryRepository.findById(filmDTO.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Catégorie non trouvée avec l'ID : " + filmDTO.getCategoryId()
                ));

        Film film = filmMapper.toEntity(filmDTO, director, category);
        return filmRepository.save(film);
    }



    @Override
    @Transactional(readOnly = true)
    public List<Film> getAll() {
        return filmRepository.findAll();
    }



    @Override
    @Transactional(readOnly = true)
    public Film getById(Long id) {
        return filmRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Film non trouvé avec l'ID : " + id
                ));
    }



    @Override
    public Film update(Long id, FilmDTO filmDTO) {
        Film existingFilm = getById(id);

        Director director = directorRepository.findById(filmDTO.getDirectorId())
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Réalisateur non trouvé avec l'ID : " + filmDTO.getDirectorId()
                ));

        Category category = categoryRepository.findById(filmDTO.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Catégorie non trouvée avec l'ID : " + filmDTO.getCategoryId()
                ));

        // Vérifier si le titre est modifié et si un autre film a déjà ce titre
        if (!existingFilm.getTitle().equals(filmDTO.getTitle()) &&
                filmRepository.existsByTitle(filmDTO.getTitle())) {
            throw new CategoryAlreadyExistsException(
                    "Un film avec le titre '" + filmDTO.getTitle() + "' existe déjà"
            );
        }

        existingFilm.setTitle(filmDTO.getTitle());
        existingFilm.setReleaseYear(filmDTO.getReleaseYear());
        existingFilm.setDuration(filmDTO.getDuration());
        existingFilm.setSynopsis(filmDTO.getSynopsis());
        existingFilm.setRating(filmDTO.getRating());
        existingFilm.setDirector(director);
        existingFilm.setCategory(category);

        return filmRepository.save(existingFilm);
    }



    @Override
    public void delete(Long id) {
        Film film = getById(id);
        filmRepository.delete(film);
    }



    @Override
    @Transactional(readOnly = true)
    public List<Film> findByTitle(String title) {
        return filmRepository.findByTitleContainingIgnoreCase(title);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Film> findByReleaseYear(Integer releaseYear) {
        return filmRepository.findByReleaseYear(releaseYear);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Film> findByRatingGreaterThanEqual(Double rating) {
        return filmRepository.findByRatingGreaterThanEqual(rating);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Film> findByCategory(Long categoryId) {
        return filmRepository.findByCategoryIdCategory(categoryId);
    }
}