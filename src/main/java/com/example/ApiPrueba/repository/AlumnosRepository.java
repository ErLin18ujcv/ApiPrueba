package com.example.ApiPrueba.repository;

import com.example.ApiPrueba.entity.Alumnos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlumnosRepository extends JpaRepository<Alumnos,Long> {
    Optional<Alumnos> findByNombre (String nombre);

}
