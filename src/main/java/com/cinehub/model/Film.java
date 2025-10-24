package com.cinehub.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFilm;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(name = "release_year")
    private Integer releaseYear;

    private Integer duration;

    @Column(columnDefinition = "TEXT")
    private String synopsis;

    private Double rating;

    // (plusieurs films peuvent avoir le même réalisateur)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "director_id")
    @JsonBackReference
    private Director director;

    // (plusieurs films peuvent avoir la même catégorie)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    public Film() {}

    public Film(String title, Integer releaseYear, Integer duration, String synopsis, Double rating) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.synopsis = synopsis;
        this.rating = rating;
    }

    public Long getIdFilm() { return idFilm; }
    public void setIdFilm(Long idFilm) { this.idFilm = idFilm; }

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

    public Director getDirector() { return director; }
    public void setDirector(Director director) { this.director = director; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}