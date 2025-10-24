package com.cinehub.repository;

import com.cinehub.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {

    Optional<Director> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT d FROM Director d WHERE LOWER(d.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(d.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Director> findByNameContainingIgnoreCase(@Param("name") String name);

    boolean existsByFirstNameAndLastName(String firstName, String lastName);
}