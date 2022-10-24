package com.example.ej157crudsecurity.entity;

import com.example.ej157crudsecurity.dto.input.ProfesorInputDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profesor_seq")
    @GenericGenerator(
            name = "profesor_seq",
            strategy = "com.example.ej157crudsecurity.entity.MyGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = MyGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = MyGenerator.VALUE_PREFIX_PARAMETER, value = "Profesor_"),
                    @org.hibernate.annotations.Parameter(name = MyGenerator.NUMBER_FORMAT_PARAMETER, value = "%d") })
    String id_profesor;

    @OneToOne
    @JoinColumn(name = "id_persona")
    @JsonIgnore
    Persona persona;

    @Column
    String coments;

    @Column(nullable = false)
    String branch;


    public Profesor(ProfesorInputDTO profesorDTO){
        this.id_profesor = profesorDTO.getId_profesor();
        this.persona = profesorDTO.getPersona();
        this.coments = profesorDTO.getComents();
        this.branch = profesorDTO.getBranch();
    }
}





//Select * from Persona;
//Select * from Profesor;
//Select * from Estudiante;
//Select * from Asignatura;
//Select * from Asignatura_estudiantes;
//
//insert into Profesor values('1', 'Frontend', 'Buen profesor', '1');
//insert into Asignatura values('1', 'Angular', 'version 1', '2022-09-20', '2022-09-10');
//
//insert into asignatura_estudiantes values('1', '1');
//
//
//
//Insert into Estudiantes values(2, 'Front', 'estudiante promedio', 40, 2, 1);
//Insert into Asignatura(ID_Asignatura, asignatura, coments, finish_date, initial_date,) values ('1', 'Angular', 'version 1', '2022-09-20', '2022-09-10');
//
//Select * from Estudiante;
//Delete from Estudiante where id_estudiante = '1'