package com.example.ej157crudsecurity.repository;

import com.example.ej157crudsecurity.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepositorio extends JpaRepository<Estudiante, String> {

//    List<EstudianteOutputDTO> findEstudiantesByPersona(String name);
}
