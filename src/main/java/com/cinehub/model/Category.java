package com.cinehub.model;

import jakarta.persistence.*;

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
}