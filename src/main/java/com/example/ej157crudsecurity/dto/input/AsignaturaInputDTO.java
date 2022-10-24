package com.example.ej157crudsecurity.dto.input;

import com.example.ej157crudsecurity.entity.Estudiante;
import lombok.Data;

import java.util.Date;

@Data
public class AsignaturaInputDTO {

    String id_asignatura;
    Estudiante estudiante;
    String asignatura;
    String coments;
    Date initial_date;
    Date finish_date;

}
