package com.example.ej157crudsecurity.dto.input;

import com.example.ej157crudsecurity.entity.Persona;
import com.example.ej157crudsecurity.entity.Profesor;
import lombok.Data;

@Data
public class EstudianteInputDTO {

    String id_estudiante;
    Integer id_persona;
    Persona persona;
    int num_hours_week;
    String coments;

    String id_profesor;
    Profesor profesor;
    String branch;

    public EstudianteInputDTO(){

    }

    public EstudianteInputDTO(String id_estudiante, Integer id_persona, int num_hours_week, String coments, String id_profesor, String branch) {
        this.id_estudiante = id_estudiante;
        this.id_persona = id_persona;
        this.num_hours_week = num_hours_week;
        this.coments = coments;
        this.id_profesor = id_profesor;
        this.branch = branch;
    }
    public EstudianteInputDTO(String id_estudiante, Persona persona, int num_hours_week, String coments, Profesor profesor, String branch) {
        this.id_estudiante = id_estudiante;
        this.persona = persona;
        this.num_hours_week = num_hours_week;
        this.coments = coments;
        this.profesor = profesor;
        this.branch = branch;
    }
}
