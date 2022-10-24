package com.example.ej157crudsecurity.controller;

import com.example.ej157crudsecurity.application.EstudianteServiceImpl;
import com.example.ej157crudsecurity.dto.input.EstudianteInputDTO;
import com.example.ej157crudsecurity.dto.output.EstudianteOutputDTO;
import com.example.ej157crudsecurity.entity.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EstudianteControlador {

    @Autowired
    EstudianteServiceImpl estudianteServiceImpl;



    @GetMapping("estudiantes/{id}")
    public EstudianteOutputDTO dameEstudiante(@PathVariable String id, @RequestParam(required = false) String outputType) {
        Estudiante e = estudianteServiceImpl.buscarEstudiantePorId(id);
        EstudianteOutputDTO estudianteOutputDTO = new EstudianteOutputDTO(e);

        if(outputType == null){
            outputType = "simple";
        }

        if(outputType.equals("full")){
            return estudianteOutputDTO;
        }


        estudianteOutputDTO.setPersonaOutputDTO(null);
        return estudianteOutputDTO;
    }



//    @PostMapping("estudiantes")
//    public List<EstudianteOutputDTO> dameEstudiantePorNombre(@RequestParam String name) {
//
//        return estudianteServiceImpl.buscarEstudiantesPorName(name);
//    }

    @GetMapping("estudiantes")
    public Iterable<EstudianteOutputDTO> dameEstudiantes() throws Exception{

        return estudianteServiceImpl.dameAllEstudiantes();
    }

    @PostMapping("estudiantes/insertar")
    public EstudianteOutputDTO insertaEstudiante(@RequestBody EstudianteInputDTO estudianteDTO) {

        return estudianteServiceImpl.insertarEstudiante(estudianteDTO);


    }

    @PutMapping("estudiantes/editar")
    public EstudianteOutputDTO editarEstudiante(@RequestParam String id, @RequestBody EstudianteInputDTO estudianteDTO) {
        return estudianteServiceImpl.editarEstudiante(id, estudianteDTO);
    }

    @DeleteMapping("estudiantes/eliminar")
    public String eliminarEstudiante(@RequestParam String id) {
        return estudianteServiceImpl.eliminarEstudiante(id);
    }


}
