package com.example.ej157crudsecurity.dto.output;

import com.example.ej157crudsecurity.entity.Estudiante;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteOutputDTO {
    String id_estudiante;
    PersonaOutputDTO personaOutputDTO;
    int num_hours_week;
    String coments;
    ProfesorOutputDTO profesorOutputDTO;
    String branch;

    public EstudianteOutputDTO(Estudiante estudiante){
        setId_estudiante(estudiante.getId_estudiante());
        setPersonaOutputDTO (new PersonaOutputDTO(estudiante.getPersona()));
        setNum_hours_week(estudiante.getNum_hours_week());
        setComents(estudiante.getComents());
        setProfesorOutputDTO(new ProfesorOutputDTO(estudiante.getProfesor()));
        setBranch(estudiante.getBranch());
    }
}
