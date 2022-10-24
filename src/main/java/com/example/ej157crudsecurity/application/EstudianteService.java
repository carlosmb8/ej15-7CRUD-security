package com.example.ej157crudsecurity.application;

import com.example.ej157crudsecurity.dto.input.EstudianteInputDTO;
import com.example.ej157crudsecurity.dto.output.EstudianteOutputDTO;
import com.example.ej157crudsecurity.entity.Estudiante;

import java.util.List;

public interface EstudianteService {
    public EstudianteOutputDTO insertarEstudiante(EstudianteInputDTO estudianteDTO);
    public EstudianteOutputDTO editarEstudiante(String id, EstudianteInputDTO estudiante);
    public String eliminarEstudiante(String id);
    public Estudiante buscarEstudiantePorId(String id);

//    public List<EstudianteOutputDTO> buscarEstudiantesPorName(String name);

    public List<EstudianteOutputDTO> dameAllEstudiantes() throws Exception;
}
