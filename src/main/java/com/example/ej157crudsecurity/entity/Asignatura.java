package com.example.ej157crudsecurity.entity;

import com.example.ej157crudsecurity.dto.input.AsignaturaInputDTO;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asignatura_seq")
    @GenericGenerator(
            name = "asignatura_seq",
            strategy = "com.example.ej157crudsecurity.entity.MyGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = MyGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = MyGenerator.VALUE_PREFIX_PARAMETER, value = "Asignatura_"),
                    @org.hibernate.annotations.Parameter(name = MyGenerator.NUMBER_FORMAT_PARAMETER, value = "%d") })
    String id_asignatura;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = @JoinColumn(name = "id_asignatura"),
            inverseJoinColumns = @JoinColumn(name = "id_estudiante")
    )
    List<Estudiante> estudiantes = new ArrayList<>();

    String asignatura;
    String coments;

    @Column(nullable = false)
    Date initial_date;

    Date finish_date;

    public Asignatura(AsignaturaInputDTO asignaturaDTO){

        this.id_asignatura = asignaturaDTO.getId_asignatura();
        this.estudiantes = (List<Estudiante>) asignaturaDTO.getEstudiante();
        this.asignatura = asignaturaDTO.getAsignatura();
        this.coments = asignaturaDTO.getComents();
        this.initial_date = asignaturaDTO.getInitial_date();
        this.finish_date = asignaturaDTO.getFinish_date();

    }


}
