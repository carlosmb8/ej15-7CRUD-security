package com.example.ej157crudsecurity.dto.input;

import com.example.ej157crudsecurity.entity.Persona;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorInputDTO {

    String id_profesor;
    Integer id_persona;
    Persona persona;
    String coments;
    String branch;

}
