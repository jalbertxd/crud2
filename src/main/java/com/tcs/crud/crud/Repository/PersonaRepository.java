package com.tcs.crud.crud.Repository;

import com.tcs.crud.crud.model.Persona;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long>{
    
}
