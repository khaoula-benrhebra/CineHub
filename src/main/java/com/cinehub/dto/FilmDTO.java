package com.cinehub.dto;

import jakarta.validation.constraints.*;

public class FilmDTO {

    @NotBlank(message = "Le titre est obligatoire")
    @Size(max = 255, message = "Le titre ne peut pas dépasser 255 caractères")
    private String title;

    @NotNull(message = "L'année de sortie est obligatoire")
    private Integer releaseYear;

    @NotNull(message = "La durée est obligatoire")
    @Min(value = 1, message = "La durée doit être supérieure à 0")
    private Integer duration;

    @Size(max = 2000, message = "Le synopsis ne peut pas dépasser 2000 caractères")
    private String synopsis;

    @DecimalMin(value = "0.0", message = "La note doit être au moins 0.0")
    @DecimalMax(value = "10.0", message = "La note ne peut pas dépasser 10.0")
    private Double rating;

    @NotNull(message = "L'ID du réalisateur est obligatoire")
    private Long directorId;

    @NotNull(message = "L'ID de la catégorie est obligatoire")
    private Long categoryId;

    public FilmDTO() {}

    public FilmDTO(String title, Integer releaseYear, Integer duration, String synopsis, Double rating, Long directorId, Long categoryId) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.synopsis = synopsis;
        this.rating = rating;
        this.directorId = directorId;
        this.categoryId = categoryId;
    }


    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getReleaseYear() { return releaseYear; }
    public void setReleaseYear(Integer releaseYear) { this.releaseYear = releaseYear; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public String getSynopsis() { return synopsis; }
    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public Long getDirectorId() { return directorId; }
    public void setDirectorId(Long directorId) { this.directorId = directorId; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
}