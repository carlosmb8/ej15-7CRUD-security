package com.example.ej157crudsecurity.dto.output;

import com.example.ej157crudsecurity.entity.Asignatura;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaOutputDTO {

    String id_asignatura;
    String asignatura;
    String coments;
    Date initial_date;
    Date finish_date;



    public AsignaturaOutputDTO(Asignatura asignatura){

        setId_asignatura(asignatura.getId_asignatura());
        setAsignatura(asignatura.getAsignatura());
        setComents(asignatura.getComents());
        setInitial_date(asignatura.getInitial_date());
        setFinish_date(asignatura.getFinish_date());
    }
}
