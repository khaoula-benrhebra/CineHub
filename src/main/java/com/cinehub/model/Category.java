package com.cinehub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Film> films = new ArrayList<>();

    public Category() {}
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }


    public Long getIdCategory() {return idCategory;}
    public void setIdCategory(Long idCategory) {this.idCategory = idCategory;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public List<Film> getFilms() {return films;}
    public void setFilms(List<Film> films) {this.films = films;}
}