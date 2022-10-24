package com.example.ej157crudsecurity.application;

import com.example.ej157crudsecurity.dto.input.AsignaturaInputDTO;
import com.example.ej157crudsecurity.dto.output.AsignaturaOutputDTO;
import com.example.ej157crudsecurity.entity.Asignatura;

import java.util.List;

public interface AsignaturaService {
    public AsignaturaOutputDTO insertarAsignatura(AsignaturaInputDTO asignaturaDTO);
    public  AsignaturaOutputDTO editarAsignatura(String id, AsignaturaInputDTO asignatura);
    public void eliminarAsignatura(String id);
    public Asignatura buscarAsignaturaPorId(String id);
//    public List<AsignaturaOutputDTO> buscarAsignaturasPorName(String name);
    public List<AsignaturaOutputDTO> dameAllAsignaturas() throws Exception;

    public void asociarAsignatura(String id_asignatura, String id_estudiante);

    public void asociarAsignaturas(List<String> id_asignatura, String id_estudiante);
}
