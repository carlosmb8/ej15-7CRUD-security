package com.example.ej157crudsecurity.application;

import com.example.ej157crudsecurity.dto.input.ProfesorInputDTO;
import com.example.ej157crudsecurity.dto.output.ProfesorOutputDTO;
import com.example.ej157crudsecurity.entity.Persona;
import com.example.ej157crudsecurity.entity.Profesor;
import com.example.ej157crudsecurity.repository.PersonaRepositorio;
import com.example.ej157crudsecurity.repository.ProfesorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    PersonaServiceImpl personaServiceImpl;

    @Autowired
    PersonaRepositorio personaRepo;
    @Autowired
    ProfesorRepositorio profesorRepo;

    @Override
    public ProfesorOutputDTO insertarProfesor(ProfesorInputDTO profesorDTO) {

        Persona persona = personaRepo.findById(profesorDTO.getId_persona()).orElseThrow(EntityNotFoundException::new);

        Profesor profesor = new Profesor(profesorDTO);
        profesor.setPersona(persona);
        profesorRepo.save(profesor);
        ProfesorOutputDTO saveOutputDTO = new ProfesorOutputDTO(profesor);
        return saveOutputDTO;

    }

    @Override
    public ProfesorOutputDTO editarProfesor(String id, ProfesorInputDTO profesorInputDTO) {
        
        Optional<Profesor> profesorOptional = profesorRepo.findById(id);
        if(profesorOptional.isEmpty()){
            throw new EntityNotFoundException();
        }

        Profesor profesor = profesorOptional.get();

        Persona persona = personaRepo.findById(profesorInputDTO.getId_persona()).orElseThrow(EntityNotFoundException::new);

        profesor.setPersona(persona);
        profesor.setComents(profesorInputDTO.getComents());
        profesor.setBranch(profesorInputDTO.getBranch());

        profesorRepo.save(profesor);
        return new ProfesorOutputDTO(profesor);
    }

    @Override
    public void eliminarProfesor(String id) {
        try {

            profesorRepo.deleteById(id);

        } catch (Exception e) {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Profesor buscarProfesorPorId(String id) {

//            Profesor p;
//            Optional<Profesor> profesorOptional = profesorRepo.findById(id);
//
//            if (profesorOptional.isPresent()) {
//                p = profesorOptional.get();
//                return new ProfesorOutputDTO(p);
//
//            } else {
//                throw new EntityNotFoundException();
//            }

        //        return Optional .ofNullable(profesorRepo.findById(id)) .orElseThrow(EntityNotFoundException::new).get();

                return profesorRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }


//    @Override
//    public List<ProfesorOutputDTO> buscarProfesorsPorName(String name) {
//        try {
//            List<ProfesorOutputDTO> listaProfesors = profesorRepo.findProfesorsByPersona(name);
//            return listaProfesors;
//        } catch (Exception e) {
//            throw new EntityNotFoundException();
//        }
//    }

        @Override
        public List<Profesor> dameAllProfesors () throws Exception {
            try {

                return profesorRepo.findAll();
            } catch (Exception e) {
                throw new Exception("No hay registros");
            }
        }
    }
