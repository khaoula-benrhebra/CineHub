package com.cinehub.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "directors")
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDirector;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(length = 100)
    private String nationality;

    private LocalDate birthDate;

    @Column(columnDefinition = "TEXT")
    private String biography;

    // (un réalisateur peut avoir plusieurs films)
    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Film> films = new ArrayList<>();

    public Director() {}

    public Director(String firstName, String lastName, String nationality, LocalDate birthDate, String biography) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.biography = biography;
    }

    public Long getIdDirector() { return idDirector; }
    public void setIdDirector(Long idDirector) { this.idDirector = idDirector; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getBiography() { return biography; }
    public void setBiography(String biography) { this.biography = biography; }

    public List<Film> getFilms() { return films; }
    public void setFilms(List<Film> films) { this.films = films; }
}