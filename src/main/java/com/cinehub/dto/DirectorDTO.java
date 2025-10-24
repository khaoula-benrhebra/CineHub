package com.cinehub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class DirectorDTO {

    @NotBlank(message = "Le prénom est obligatoire")
    @Size(min = 2, max = 100, message = "Le prénom doit contenir entre 2 et 100 caractères")
    private String firstName;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
    private String lastName;

    @Size(max = 100, message = "La nationalité ne peut pas dépasser 100 caractères")
    private String nationality;

    @Past(message = "La date de naissance doit être dans le passé")
    @NotNull(message = "La date de naissance est obligatoire")
    private LocalDate birthDate;

    @Size(max = 2000, message = "La biographie ne peut pas dépasser 2000 caractères")
    private String biography;

    public DirectorDTO() {}

    public DirectorDTO(String firstName, String lastName, String nationality, LocalDate birthDate, String biography) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.biography = biography;
    }

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
}