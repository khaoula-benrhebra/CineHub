package com.cinehub.mapper;

import com.cinehub.model.Director;
import com.cinehub.dto.DirectorDTO;
import org.springframework.stereotype.Component;

@Component
public class DirectorMapper {

    public DirectorDTO toDTO(Director director) {
        if (director == null) {
            return null;
        }

        return new DirectorDTO(
                director.getFirstName(),
                director.getLastName(),
                director.getNationality(),
                director.getBirthDate(),
                director.getBiography()
        );
    }

    public Director toEntity(DirectorDTO directorDTO) {
        if (directorDTO == null) {
            return null;
        }

        Director director = new Director();
        director.setFirstName(directorDTO.getFirstName());
        director.setLastName(directorDTO.getLastName());
        director.setNationality(directorDTO.getNationality());
        director.setBirthDate(directorDTO.getBirthDate());
        director.setBiography(directorDTO.getBiography());

        return director;
    }
}