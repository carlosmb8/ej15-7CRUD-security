package com.example.ej157crudsecurity.repository;

import com.example.ej157crudsecurity.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepositorio extends JpaRepository<Persona, Integer> {

    List<Persona> findByName(String name);
    Persona findByUsuario(String usuario);
}
