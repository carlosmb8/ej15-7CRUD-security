package com.example.ej157crudsecurity.application;

import com.example.ej157crudsecurity.dto.input.ProfesorInputDTO;
import com.example.ej157crudsecurity.dto.output.ProfesorOutputDTO;
import com.example.ej157crudsecurity.entity.Profesor;

import java.util.List;

public interface ProfesorService {
    public ProfesorOutputDTO insertarProfesor(ProfesorInputDTO profesorDTO);
    public ProfesorOutputDTO editarProfesor(String id, ProfesorInputDTO profesorInputDTO);
    public void eliminarProfesor(String id);
    public Profesor buscarProfesorPorId(String id);
//    public List<ProfesorOutputDTO> buscarProfesorsPorName(String name);
    public List<Profesor> dameAllProfesors() throws Exception;
}
