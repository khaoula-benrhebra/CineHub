package com.cinehub.repository;

import com.cinehub.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    /// Recherche des films par titre insensible à la casse
    List<Film> findByTitleContainingIgnoreCase(String title);


    /// Recherche des films par année de sortie
    List<Film> findByReleaseYear(Integer releaseYear);


    ///Recherche des films avec une note minimale
    List<Film> findByRatingGreaterThanEqual(Double rating);



     ///Permet d'obtenir la filmographie complète d'un réalisateur
    List<Film> findByDirectorIdDirector(Long directorId);

    /**
     * US19: Recherche tous les films d'une catégorie spécifique
     * Permet de lister les films par catégorie
     */
    List<Film> findByCategoryIdCategory(Long categoryId);


    ///Films par réalisateur ET catégorie
    @Query("SELECT f FROM Film f WHERE f.director.idDirector = :directorId AND f.category.idCategory = :categoryId")
    List<Film> findByDirectorAndCategory(@Param("directorId") Long directorId, @Param("categoryId") Long categoryId);


    ///Vérifie si un film existe déjà par titre
    boolean existsByTitle(String title);
}