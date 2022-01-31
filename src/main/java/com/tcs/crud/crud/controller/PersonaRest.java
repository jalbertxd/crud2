package com.tcs.crud.crud.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.tcs.crud.crud.model.Persona;
import com.tcs.crud.crud.service.PersonaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/persona/")
public class PersonaRest {
    
    @Autowired
    private PersonaService personaService;

    @PostMapping
    public ResponseEntity<Persona> addPersona(@RequestBody Persona persona){
        Persona temporal = personaService.addPersona(persona);

        try{
            return ResponseEntity.created(new URI("/api/persona/"+temporal.getId())).body(temporal);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Persona>> getPersonas(){
        return ResponseEntity.ok(personaService.getPersonas());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Optional<Persona>> getPersona(@PathVariable Long id){
        return ResponseEntity.ok(personaService.getPersona(id));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePersona(@RequestBody Persona persona){
        personaService.deletePersona(persona);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deletePersonaById(@PathVariable Long id){
        Optional<Persona> temporal = personaService.getPersona(id);

        if(temporal.isPresent()){
            personaService.deletePersonaById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<Persona> updatePersona(@RequestBody Persona persona){
        Optional<Persona> temporal = personaService.getPersona(persona.getId());

        if(temporal.isPresent()){
            Persona updatePersona = temporal.get();
            updatePersona.setNombre(persona.getNombre());
            updatePersona.setApellido(persona.getApellido());
            updatePersona.setCorreo(persona.getCorreo());
            personaService.addPersona(updatePersona);
            return ResponseEntity.ok(updatePersona);
        }else{
            return ResponseEntity.notFound().build();
        }

    }
}
