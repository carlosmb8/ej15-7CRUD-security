package com.example.ej157crudsecurity.application;

import com.example.ej157crudsecurity.dto.input.AsignaturaInputDTO;
import com.example.ej157crudsecurity.dto.output.AsignaturaOutputDTO;
import com.example.ej157crudsecurity.entity.Asignatura;
import com.example.ej157crudsecurity.entity.Estudiante;
import com.example.ej157crudsecurity.repository.AsignaturaRepositorio;
import com.example.ej157crudsecurity.repository.EstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    @Autowired
    AsignaturaRepositorio asignaturaRepo;

    @Autowired
    EstudianteRepositorio estudianteRepo;

    @Override
    public AsignaturaOutputDTO insertarAsignatura(AsignaturaInputDTO asignaturaDTO){
            
            Asignatura asignatura = new Asignatura(asignaturaDTO);
            asignaturaRepo.save(asignatura);
            AsignaturaOutputDTO saveOutputDTO = new AsignaturaOutputDTO(asignatura);
            return saveOutputDTO;
    }


    @Override
    public AsignaturaOutputDTO editarAsignatura(String id, AsignaturaInputDTO asignaturaInputDTO){

        Optional<Asignatura> asignaturaOptional = asignaturaRepo.findById(id);
        if(asignaturaOptional.isEmpty()){
            throw new EntityNotFoundException();
        }

        Asignatura asignatura = asignaturaOptional.get();

        asignatura.setAsignatura(asignaturaInputDTO.getAsignatura());
        asignatura.setComents(asignaturaInputDTO.getComents());
        asignatura.setInitial_date(asignaturaInputDTO.getInitial_date());
        asignatura.setFinish_date(asignaturaInputDTO.getFinish_date());

        asignaturaRepo.save(asignatura);

        return new AsignaturaOutputDTO(asignatura);

    }

    @Override
    public void eliminarAsignatura(String id) {
        try {

            asignaturaRepo.deleteById(id);

        } catch (Exception e) {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Asignatura buscarAsignaturaPorId(String id) {

//        return Optional .ofNullable(asignaturaRepo.findById(id)) .orElseThrow(EntityNotFoundException::new).get();

        return asignaturaRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

//    @Override
//    public List<AsignaturaOutputDTO> buscarAsignaturaPorName(String name) {
//        try {
//            List<AsignaturaOutputDTO> listaAsignaturas = asignaturaRepo.findAsignaturas(name);
//            return listaAsignaturas;
//        } catch (Exception e) {
//            throw new EntityNotFoundException();
//        }
//    }

    @Override
    public List<AsignaturaOutputDTO> dameAllAsignaturas() throws Exception {
        try {
            List<AsignaturaOutputDTO> listaAsignaturasDTO = new ArrayList<>();
            List<Asignatura> listaAsignaturas = asignaturaRepo.findAll();
            listaAsignaturas.forEach(asignatura -> {listaAsignaturasDTO.add(new AsignaturaOutputDTO(asignatura));});
            return listaAsignaturasDTO;

        } catch (Exception e) {
            throw new Exception("No hay registros");
        }
    }

    @Override
    @Transactional
    public void asociarAsignatura(String id_asignatura, String id_estudiante) {

        Asignatura asignatura = asignaturaRepo.findById(id_asignatura).orElseThrow(EntityNotFoundException::new);
        Estudiante estudiante = estudianteRepo.findById(id_estudiante).orElseThrow(EntityNotFoundException::new);

//        estudiante.getAsignaturas().add(asignatura);
//        estudianteRepo.save(estudiante);

        asignatura.getEstudiantes().add(estudiante);
//        asignaturaRepo.save(asignatura);

//
    }

    @Override
    @Transactional
    public void asociarAsignaturas(List<String>listaIdAsignaturas, String id_estudiante) {

        Estudiante estudiante = estudianteRepo.findById(id_estudiante).orElseThrow(EntityNotFoundException::new);

//        for(int i=0; i<listaIdAsignaturas.size(); i++){

//            Asignatura asignatura = asignaturaRepo.findById(listaIdAsignaturas.get(i)).orElseThrow(EntityNotFoundException::new);

//        }

        listaIdAsignaturas.forEach(id -> {
            Asignatura asignatura = asignaturaRepo.findById(id).orElseThrow(EntityNotFoundException::new);
            asignatura.getEstudiantes().add(estudiante);
        });

    }
}
