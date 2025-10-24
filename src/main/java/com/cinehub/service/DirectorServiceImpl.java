package com.cinehub.service;

import com.cinehub.dto.DirectorDTO;
import com.cinehub.exception.CategoryAlreadyExistsException;
import com.cinehub.exception.CategoryNotFoundException;
import com.cinehub.mapper.DirectorMapper;
import com.cinehub.model.Director;
import com.cinehub.model.Film;
import com.cinehub.repository.DirectorRepository;
import com.cinehub.repository.FilmRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;
    private final FilmRepository filmRepository;
    private final DirectorMapper directorMapper;

    public DirectorServiceImpl(DirectorRepository directorRepository,
                               FilmRepository filmRepository,
                               DirectorMapper directorMapper) {
        this.directorRepository = directorRepository;
        this.filmRepository = filmRepository;
        this.directorMapper = directorMapper;
    }


    @Override
    public Director create(DirectorDTO directorDTO) {
        if (directorRepository.existsByFirstNameAndLastName(directorDTO.getFirstName(), directorDTO.getLastName())) {
            throw new CategoryAlreadyExistsException(
                    "Un réalisateur avec le nom '" + directorDTO.getFirstName() + " " + directorDTO.getLastName() + "' existe déjà"
            );
        }

        Director director = directorMapper.toEntity(directorDTO);
        return directorRepository.save(director);
    }



    @Override
    @Transactional(readOnly = true)
    public List<Director> getAll() {
        return directorRepository.findAll();
    }



    @Override
    @Transactional(readOnly = true)
    public Director getById(Long id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Réalisateur non trouvé avec l'ID : " + id
                ));
    }



     ///Vérifie l'unicité du couple prénom/nom si celui-ci est modifié
    @Override
    public Director update(Long id, DirectorDTO directorDTO) {
        Director existingDirector = getById(id);

        if (!existingDirector.getFirstName().equals(directorDTO.getFirstName()) ||
                !existingDirector.getLastName().equals(directorDTO.getLastName())) {

            if (directorRepository.existsByFirstNameAndLastName(directorDTO.getFirstName(), directorDTO.getLastName())) {
                throw new CategoryAlreadyExistsException(
                        "Un réalisateur avec le nom '" + directorDTO.getFirstName() + " " + directorDTO.getLastName() + "' existe déjà"
                );
            }
        }

        existingDirector.setFirstName(directorDTO.getFirstName());
        existingDirector.setLastName(directorDTO.getLastName());
        existingDirector.setNationality(directorDTO.getNationality());
        existingDirector.setBirthDate(directorDTO.getBirthDate());
        existingDirector.setBiography(directorDTO.getBiography());

        return directorRepository.save(existingDirector);
    }


    @Override
    public void delete(Long id) {
        Director director = getById(id);

        // Vérifier si le réalisateur a des films
        List<Film> films = filmRepository.findByDirectorIdDirector(id);
        if (!films.isEmpty()) {
            throw new RuntimeException(
                    "Impossible de supprimer le réalisateur car il a des films associés"
            );
        }

        directorRepository.delete(director);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Director> findByName(String name) {
        return directorRepository.findByNameContainingIgnoreCase(name);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Film> getFilmsByDirector(Long directorId) {
        getById(directorId);
        return filmRepository.findByDirectorIdDirector(directorId);
    }
}