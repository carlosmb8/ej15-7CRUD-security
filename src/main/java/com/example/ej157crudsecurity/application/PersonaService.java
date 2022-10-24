package com.example.ej157crudsecurity.application;

import com.example.ej157crudsecurity.dto.input.PersonaInputDTO;
import com.example.ej157crudsecurity.dto.output.PersonaOutputDTO;
import com.example.ej157crudsecurity.entity.Persona;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface PersonaService {
    public PersonaOutputDTO insertarPersona(PersonaInputDTO personaDTO);
    public PersonaOutputDTO editarPersona(Integer id, PersonaInputDTO personaInputDTO);
    public void eliminarPersona(Integer id);
    public PersonaOutputDTO buscarPersonaPorId(Integer id);
    public List<PersonaOutputDTO> buscarPersonaPorName(String name);
    public List<Persona> dameAllPersonas() throws Exception;

    public List<Persona> damePersonasPorCriterioPaginado(HashMap<String, Object> conditions, Integer offset, Integer limit);


}
