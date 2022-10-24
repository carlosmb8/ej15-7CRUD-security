package com.example.ej157crudsecurity.controller;

import com.example.ej157crudsecurity.application.AsignaturaServiceImpl;
import com.example.ej157crudsecurity.dto.input.AsignaturaInputDTO;
import com.example.ej157crudsecurity.dto.output.AsignaturaOutputDTO;
import com.example.ej157crudsecurity.entity.Asignatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class AsignaturaControlador {

    @Autowired
    AsignaturaServiceImpl asignaturaServiceImpl;

    @GetMapping("asignaturas/{id}")
    public Asignatura dameAsignatura(@PathVariable String id, @RequestParam(required = false) String outputType) {
        return asignaturaServiceImpl.buscarAsignaturaPorId(id);
    }

//    @PostMapping("asignaturas")
//    public List<AsignaturaOutputDTO> dameAsignaturaPorNombre(@RequestParam String name) {
//
//        return asignaturaServiceImpl.buscarAsignatura_AsignaturasPorName(name);
//    }

    @GetMapping("asignaturas")
    public Iterable<AsignaturaOutputDTO> dameAsignaturas() throws Exception{

        return asignaturaServiceImpl.dameAllAsignaturas();
    }

    @PostMapping("asignaturas/insertar")
    public AsignaturaOutputDTO insertaAsignatura(@RequestBody AsignaturaInputDTO asignaturaDTO) {

        return asignaturaServiceImpl.insertarAsignatura(asignaturaDTO);


    }

    @PutMapping("asignaturas/editar")
    public AsignaturaOutputDTO editarAsignatura(@RequestParam String id, @RequestBody AsignaturaInputDTO asignaturaInputDTO) {

        return asignaturaServiceImpl.editarAsignatura(id, asignaturaInputDTO);
    }

    @DeleteMapping("asignaturas/eliminar")
    public String eliminarAsignatura(@RequestParam String id) {
        asignaturaServiceImpl.eliminarAsignatura(id);
        return "La asignatura con el id: " + id + " ha sido borrada correctamete";
    }




    @PostMapping("asignaturas/asociar/{id_asignatura}/{id_estudiante}")
    public void asociarAsignautra(@PathVariable String id_asignatura, @PathVariable String id_estudiante){
        asignaturaServiceImpl.asociarAsignatura(id_asignatura, id_estudiante);
    }

    @PostMapping("asignaturas/asociarlista/{id_estudiante}")
    public void asociarAsignautras(@RequestBody ArrayList<String> listaIdAsignaturas, @PathVariable String id_estudiante){
        asignaturaServiceImpl.asociarAsignaturas(listaIdAsignaturas, id_estudiante);
    }

}
