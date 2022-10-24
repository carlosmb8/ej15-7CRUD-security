package com.example.ej157crudsecurity.entity;

import com.example.ej157crudsecurity.dto.input.EstudianteInputDTO;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Estudiante {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estudiante1_seq")
@GenericGenerator(
        name = "estudiante1_seq",
        strategy = "com.example.ej157crudsecurity.entity.MyGenerator",
        parameters = {
                @org.hibernate.annotations.Parameter(name = MyGenerator.INCREMENT_PARAM, value = "1"),
                @org.hibernate.annotations.Parameter(name = MyGenerator.VALUE_PREFIX_PARAMETER, value = "Estudiante_"),
                @org.hibernate.annotations.Parameter(name = MyGenerator.NUMBER_FORMAT_PARAMETER, value = "%d") })
    String id_estudiante;

    @OneToOne
    @JoinColumn(name = "id_persona")
    Persona persona;

    @Column(name = "horas_por_semana")
    int num_hours_week;

    @Column(name = "comentarios")
    String coments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    Profesor profesor;

    @Column(name = "rama")
    String branch;

    @ManyToMany(mappedBy ="estudiantes", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Asignatura> asignaturas = new ArrayList<>();

    public Estudiante(EstudianteInputDTO estudianteDTO) {
        this.id_estudiante = estudianteDTO.getId_estudiante();
        this.persona = estudianteDTO.getPersona();
        this.num_hours_week = estudianteDTO.getNum_hours_week();
        this.coments = estudianteDTO.getComents();
        this.profesor = estudianteDTO.getProfesor();
        this.branch = estudianteDTO.getBranch();
    }
}
