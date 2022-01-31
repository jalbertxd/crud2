package com.tcs.crud.crud.service;

import java.util.List;
import java.util.Optional;

import com.tcs.crud.crud.Repository.PersonaRepository;
import com.tcs.crud.crud.model.Persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {
    
    @Autowired
    private PersonaRepository personaRepository;

    public Persona addPersona(Persona persona){
        return personaRepository.save(persona);
    }

    public List<Persona> getPersonas(){
        return personaRepository.findAll();
    }

    public Optional<Persona> getPersona(Long id){
        return personaRepository.findById(id);
    }

    public void deletePersona(Persona persona){
        personaRepository.delete(persona);
    }

    public void deletePersonaById(Long id){
        personaRepository.deleteById(id);
    }
}
