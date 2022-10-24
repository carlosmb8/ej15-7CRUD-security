package com.example.ej157crudsecurity.application;

import com.example.ej157crudsecurity.dto.input.PersonaInputDTO;
import com.example.ej157crudsecurity.dto.output.PersonaOutputDTO;
import com.example.ej157crudsecurity.entity.Persona;
import com.example.ej157crudsecurity.excepciones.UnprocessableEntityException;
import com.example.ej157crudsecurity.repository.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

import static com.example.ej157crudsecurity.controller.PersonaControlador.*;

@Service
public class PersonaServiceImpl implements PersonaService{

    @Autowired
    PersonaRepositorio personaRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PersonaOutputDTO insertarPersona(PersonaInputDTO personaDTO) {
        if (personaDTO.getUsuario().length() > 10) {
            throw new UnprocessableEntityException();
        } else {
            Persona persona = new Persona(personaDTO);
            personaRepo.save(persona);
            PersonaOutputDTO saveOutputDTO = new PersonaOutputDTO(persona);
            return saveOutputDTO;
        }
    }

    @Override
    public PersonaOutputDTO editarPersona(Integer id, PersonaInputDTO personaInputDTO) {

        Optional<Persona> personaOptional = personaRepo.findById(id);
        if (personaOptional.isEmpty()) {

            throw new EntityNotFoundException();
        }

        Persona persona = personaOptional.get();

        persona.setUsuario(personaInputDTO.getUsuario());
        persona.setPassword(personaInputDTO.getPassword());
        persona.setName(personaInputDTO.getName());
        persona.setSurname(personaInputDTO.getSurname());
        persona.setCompany_email(personaInputDTO.getCompany_email());
        persona.setPersonal_email(personaInputDTO.getPersonal_email());
        persona.setCity(personaInputDTO.getCity());
        persona.setActive(personaInputDTO.isActive());
        persona.setImagen_url(personaInputDTO.getImagen_url());

        personaRepo.save(persona);

        return new PersonaOutputDTO(persona);

    }

    @Override
    public void eliminarPersona(Integer id) {
        try {

            personaRepo.deleteById(id);

        } catch (Exception e) {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public PersonaOutputDTO buscarPersonaPorId(Integer id) {

//        return Optional .ofNullable(personaRepo.findById(id)) .orElseThrow(EntityNotFoundException::new).get();

                Persona persona = personaRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        return new PersonaOutputDTO(persona);
    }

    @Override
    public List<PersonaOutputDTO> buscarPersonaPorName(String name) {
        try {
            List<Persona> listaPersonas = personaRepo.findByName(name);
            List<PersonaOutputDTO> listaPersonasDTO = new ArrayList<>();
            listaPersonas.forEach((p) -> {
                listaPersonasDTO.add(new PersonaOutputDTO(p));
            });
            return listaPersonasDTO;
        } catch (Exception e) {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<Persona> dameAllPersonas() throws Exception {
        try {

            return personaRepo.findAll();
        } catch (Exception e) {
            throw new Exception("No hay registros");
        }
    }

    @Override
    public List<Persona> damePersonasPorCriterioPaginado(HashMap<String, Object> conditions, Integer offset, Integer limit) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Persona> query = cb.createQuery(Persona.class);
        Root<Persona> root = query.from(Persona.class);

        List<Predicate> predicates = new ArrayList<>();

        conditions.forEach((field, value) ->
        {
            switch (field) {
                case "usuario":
                    System.out.println(value);
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                case "name":
                    System.out.println(value);
                    predicates.add(cb.equal(root.get(field), (String) value));
                case "surname":
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                case "created_date":
                    String dateCondition = (String) conditions.get("dateCondition");
                    System.out.println(dateCondition);
                    if (dateCondition != null) {

                        switch (dateCondition) {
                            case GREATER_THAN:
                                predicates.add(cb.greaterThan(root.<Date>get(field), (Date) value));
                                break;
                            case LESS_THAN:
                                predicates.add(cb.lessThan(root.<Date>get(field), (Date) value));
                                break;
                            case EQUAL:
                                predicates.add(cb.equal(root.<Date>get(field), (Date) value));
                                break;
                        }
                    }
                    break;
            }
        });
        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(query).setMaxResults(limit = 2).setFirstResult(offset = 0).getResultList();
    }

}
