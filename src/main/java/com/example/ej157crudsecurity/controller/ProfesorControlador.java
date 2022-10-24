package com.example.ej157crudsecurity.controller;

import com.example.ej157crudsecurity.application.ProfesorServiceImpl;
import com.example.ej157crudsecurity.dto.input.ProfesorInputDTO;
import com.example.ej157crudsecurity.dto.output.ProfesorOutputDTO;
import com.example.ej157crudsecurity.entity.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfesorControlador {

    @Autowired
    ProfesorServiceImpl profesorServiceImpl;


    @GetMapping("profesores/{id}")
    public Profesor dameProfesor(@PathVariable String id) {
        return profesorServiceImpl.buscarProfesorPorId(id);
    }

//    @PostMapping("profesores")
//    public List<ProfesorOutputDTO> dameProfesorPorNombre(@RequestParam String name) {
//
//        return profesorServiceImpl.buscarProfesoresPorName(name);
//    }

    @GetMapping("profesores")
    public Iterable<Profesor> dameProfesores() throws Exception{

        return profesorServiceImpl.dameAllProfesors();
    }

    @PostMapping("profesores/insertar")
    public String insertaProfesor(@RequestBody ProfesorInputDTO profesorDTO) {

        profesorServiceImpl.insertarProfesor(profesorDTO);

        return "Profesor insertado correctamente";

    }

    @PutMapping("profesores/editar")
    public ProfesorOutputDTO editarProfesor(@RequestParam String id, @RequestBody ProfesorInputDTO profesor) {
        return profesorServiceImpl.editarProfesor(id, profesor);
    }

    @DeleteMapping("profesores/eliminar")
    public String eliminarProfesor(@RequestParam String id) {
        profesorServiceImpl.eliminarProfesor(id);
        return "El profesor con el id: " + id + " ha sido borrado correctamete";
    }
}
